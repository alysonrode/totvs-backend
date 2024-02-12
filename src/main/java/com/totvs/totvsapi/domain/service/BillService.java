package com.totvs.totvsapi.domain.service;

import com.totvs.totvsapi.domain.entity.Bill;
import com.totvs.totvsapi.domain.entity.SituationEnum;
import com.totvs.totvsapi.domain.exception.BillNotFoundException;
import com.totvs.totvsapi.domain.filter.BillFilter;
import com.totvs.totvsapi.domain.filter.PaymentFilter;
import com.totvs.totvsapi.infrastructure.model.BillModel;
import com.totvs.totvsapi.infrastructure.repository.BillRepository;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BillService implements IBillService
{
	@Autowired
	BillRepository repository;

	@Override
	public UUID createBill(Bill bill)
	{
		BillModel model = new BillModel(bill.getDueDate(), bill.getPayday(), bill.getValue(),
			bill.getDescription(), bill.getSituation());
		var saved = repository.save(model);
		return saved.getId();
	}

	public Bill getBill(UUID id)
	{
		var billModel = repository.findById(id);
		return new Bill().build(billModel.get());
	}

	@Override
	public void updateBill(Bill bill) throws BillNotFoundException
	{
		var optionalBillModel = repository.findById(bill.getId());
		if (optionalBillModel.isEmpty())
		{
			throw new BillNotFoundException(bill.getId());
		}

		var model = optionalBillModel.get();
		model.setDescription(bill.getDescription());
		model.setDueDate(bill.getDueDate());
		model.setPayday(bill.getPayday());
		model.setSituation(bill.getSituation());
		model.setValue(bill.getValue());

		repository.saveAndFlush(model);
	}

	@Override
	public void updateSituation(UUID uuid, SituationEnum situation) throws BillNotFoundException
	{
		var optionalBillModel = repository.findById(uuid);
		if (optionalBillModel.isEmpty())
		{
			throw new BillNotFoundException(uuid);
		}

		var model = optionalBillModel.get();
		model.setSituation(situation);
		repository.saveAndFlush(model);
	}

	@Override
	public List<Bill> getBills(BillFilter filter)
	{
		List<BillModel> models = repository.findByFilter(filter.getDueDate(), filter.getDescription());
		return models.stream().map(billModel -> new Bill().build(billModel)).toList();
	}

	@Override
	public BigDecimal getTotalPayment(PaymentFilter filter)
	{
		List<BigDecimal> values = repository.findByPeriod(filter.getInitialDate(), filter.getEndDate());
		return values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public List<UUID> createFromCSV(MultipartFile file)
	{
		File tempDir = null;
		File targetFile = null;
		List<UUID> created = new ArrayList<>();
		try
		{
			InputStream initialStream = file.getInputStream();
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			tempDir = Files.createTempDirectory("csv_import").toFile();
			targetFile = new File(tempDir.getAbsolutePath() + "/temp_file.csv");

			try (OutputStream outStream = new FileOutputStream(targetFile)) {
				outStream.write(buffer);
			}
			try(BufferedReader reader = new BufferedReader(new FileReader(targetFile)))
			{
				String line;
				while ((line = reader.readLine()) != null) {
					if(line.startsWith("dueDate")){
						continue;
					}
					String[] array = line.split(",");
					var bill = new Bill().build(array);
					var uuid = createBill(bill);
					created.add(uuid);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			if (targetFile != null)
			{
				targetFile.delete();
			}
			if (tempDir != null)
			{
				tempDir.delete();
			}
		}
		return created;
	}
}

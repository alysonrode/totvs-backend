package com.totvs.totvsapi.bill.domain.controller;

import com.totvs.totvsapi.bill.domain.entity.Bill;
import com.totvs.totvsapi.bill.domain.filter.BillFilter;
import com.totvs.totvsapi.bill.domain.entity.SituationEnum;
import com.totvs.totvsapi.bill.domain.exception.BillNotFoundException;
import com.totvs.totvsapi.bill.domain.filter.PaymentFilter;
import com.totvs.totvsapi.bill.domain.service.IBillService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/bill")
public class BillController
{
	@Autowired
	IBillService billService;
	@PostMapping("")
	public UUID createBill(@RequestBody final Bill bill)
	{
		return billService.createBill(bill);
	}

	@GetMapping("/find-by-id/{uuid}")
	public Bill getBill(@PathVariable UUID uuid)
	{
		return billService.getBill(uuid);
	}
	@PutMapping("")
	public void updateBill(@RequestBody final Bill bill)
	{
		try
		{
			billService.updateBill(bill);
		}
		catch (BillNotFoundException notFoundException)
		{
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, notFoundException.getMessage()
			);
		}
	}
	@PatchMapping("/update-situation/{uuid}")
	public void updateSituation(@RequestBody final SituationEnum situation, @PathVariable UUID uuid)
	{
		try
		{
			billService.updateSituation(uuid, situation);
		}
		catch (BillNotFoundException notFoundException)
		{
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, notFoundException.getMessage()
			);
		}
	}

	@GetMapping("")
	public List<Bill> getBills(@RequestBody BillFilter filter)
	{
		return billService.getBills(filter);
	}

	@GetMapping("/get-total")
	public BigDecimal getTotal(@RequestBody PaymentFilter filter)
	{
		return billService.getTotalPayment(filter);
	}

	@PostMapping("/import-from-csv")
	public List<UUID> importFromCSV(@RequestParam("file") MultipartFile file)
	{
		return billService.createFromCSV(file);
	}


}

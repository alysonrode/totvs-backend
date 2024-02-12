package com.totvs.totvsapi.domain.service;


import com.totvs.totvsapi.bill.domain.entity.Bill;
import com.totvs.totvsapi.bill.domain.entity.SituationEnum;
import com.totvs.totvsapi.bill.domain.exception.BillNotFoundException;
import com.totvs.totvsapi.bill.domain.service.BillService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


@SpringBootTest
public class BillServiceTest
{
	@Autowired
	BillService service;

	@BeforeEach
	public void setup()
	{

	}

	@Test
	public void asserBillWasCreated()
	{
		Bill bill = new Bill(new GregorianCalendar(), new GregorianCalendar(), new BigDecimal("4000"),
			"Teste", SituationEnum.OPEN);
		UUID result = service.createBill(bill);
		Assertions.assertNotNull(result);

	}

	@Test
	public void assertBillNotFound()
	{
		UUID nonExistentUUID = UUID.randomUUID();
		Assertions.assertThrows(NoSuchElementException.class, () -> service.getBill(nonExistentUUID));
	}

	@Test
	public void assertFindBill()
	{
		Bill bill = new Bill(new GregorianCalendar(), new GregorianCalendar(), new BigDecimal("4000"),
			"Teste", SituationEnum.OPEN);
		UUID uuid = service.createBill(bill);


		Bill result = service.getBill(uuid);
		Assertions.assertEquals(uuid, result.getId());
	}

	@Test
	public void assertUpdateBill()
	{
		Bill bill = new Bill(new GregorianCalendar(), new GregorianCalendar(), new BigDecimal("4000"),
			"Teste", SituationEnum.OPEN);
		UUID uuid = service.createBill(bill);

		bill.setDescription("TesteAlterado");
		try
		{
			service.updateBill(bill);
		}
		catch (Exception e)
		{
			Assertions.fail();
		}
		Bill result = service.getBill(uuid);
		Assertions.assertEquals("TesteAlterado", result.getDescription());
	}

	@Test
	public void assertUpdateSituation()
	{
		Bill bill = new Bill(new GregorianCalendar(), new GregorianCalendar(), new BigDecimal("4000"),
			"Teste", SituationEnum.OPEN);
		UUID uuid = service.createBill(bill);

		try
		{
			service.updateSituation(uuid, SituationEnum.PAID);
		}
		catch (BillNotFoundException e)
		{
			Assertions.fail();
		}
	}
	@Test
	public void createFromCSV()
	{
		String filename = "totvs-api-consume.csv";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());

		Path path = file.toPath();
		String contentType = "text/csv";
		byte[] content = null;

		try
		{
			content = Files.readAllBytes(path);
		}
		catch (final IOException e)
		{
			Assertions.fail();
		}
		MultipartFile multipartFile = new MockMultipartFile(filename, filename, contentType, content);

		var result = service.createFromCSV(multipartFile);
		Assertions.assertEquals(5, result.size());

	}


}

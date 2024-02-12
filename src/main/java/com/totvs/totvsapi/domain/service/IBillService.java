package com.totvs.totvsapi.domain.service;

import com.totvs.totvsapi.domain.entity.Bill;
import com.totvs.totvsapi.domain.entity.SituationEnum;
import com.totvs.totvsapi.domain.exception.BillNotFoundException;
import com.totvs.totvsapi.domain.filter.BillFilter;
import com.totvs.totvsapi.domain.filter.PaymentFilter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface IBillService
{
	UUID createBill(final Bill bill);
	Bill getBill(UUID id);
	void updateBill(final Bill bill) throws BillNotFoundException;
	void updateSituation(UUID id, SituationEnum situation) throws BillNotFoundException;
	List<Bill> getBills(BillFilter filter);
	BigDecimal getTotalPayment(PaymentFilter filter);
	List<UUID> createFromCSV(MultipartFile file);
}

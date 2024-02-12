package com.totvs.totvsapi.bill.infrastructure.repository;

import com.totvs.totvsapi.bill.infrastructure.model.BillModel;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

@Repository
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface BillRepository extends JpaRepository<BillModel, UUID>
{
	@Query("SELECT bill FROM BillModel bill WHERE bill.dueDate < ?1 and bill.description like %?2%")
	List<BillModel> findByFilter(GregorianCalendar dueDate, String description);

	@Query("SELECT bill.value FROM BillModel bill WHERE bill.payday > ?1 and bill.payday < ?2")
	List<BigDecimal> findByPeriod(GregorianCalendar startDate, GregorianCalendar endDate);
}

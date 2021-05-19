package com.pro.acc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.acc.entity.Expenditure;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

	List<Expenditure> findByExpenditureDate(Date expenditureDate);

	List<Expenditure> findByExpenditureDateBetween(Date expenditureStartDate, Date expenditureEndDate);
}

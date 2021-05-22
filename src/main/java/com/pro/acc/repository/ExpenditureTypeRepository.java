package com.pro.acc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.acc.entity.ExpenditureType;

@Repository
public interface ExpenditureTypeRepository extends JpaRepository<ExpenditureType, Long> {

	List<ExpenditureType> findByTypeIsNull();

	List<ExpenditureType> findByType(ExpenditureType type);
}

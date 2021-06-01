package com.pro.acc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.acc.entity.ExpenditureMasterType;

@Repository
public interface ExpenditureMasterTypeRepository extends JpaRepository<ExpenditureMasterType, Long> {

	Optional<ExpenditureMasterType> findByName(String name);
}

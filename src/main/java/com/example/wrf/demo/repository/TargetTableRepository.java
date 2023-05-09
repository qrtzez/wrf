package com.example.wrf.demo.repository;

import com.example.wrf.demo.entity.TargetTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetTableRepository extends JpaRepository<TargetTable, Long> {

    TargetTable findByFirstNameAndYearOld(String name, Integer age);

    TargetTable findByKey(String key);
}

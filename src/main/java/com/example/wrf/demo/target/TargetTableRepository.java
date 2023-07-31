package com.example.wrf.demo.target;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetTableRepository extends JpaRepository<TargetTable, Long> {

    TargetTable findByFirstNameAndYearOld(String name, Integer age);

    TargetTable findByKeyP(String key);

    TargetTable findByFirstName(String name);
}

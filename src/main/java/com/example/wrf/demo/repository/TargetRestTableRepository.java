package com.example.wrf.demo.repository;

import com.example.wrf.demo.entity.TargetRestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRestTableRepository extends JpaRepository<TargetRestTable, Long> {

    TargetRestTable findByName(String name);
}

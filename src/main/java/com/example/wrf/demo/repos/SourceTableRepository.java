package com.example.wrf.demo.repos;

import com.example.wrf.demo.entity.SourceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {
}

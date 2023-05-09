package com.example.wrf.demo.repos;

import com.example.wrf.demo.entity.SourceRestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRestTableRepository extends JpaRepository<SourceRestTable, Long> {
}

package com.example.wrf.demo.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRestTableRepository extends JpaRepository<SourceRestTable, Long> {
}

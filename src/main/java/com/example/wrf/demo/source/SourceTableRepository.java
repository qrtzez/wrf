package com.example.wrf.demo.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {

    SourceView findViewById(Long id);
}

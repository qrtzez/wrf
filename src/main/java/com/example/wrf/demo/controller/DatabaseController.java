package com.example.wrf.demo.controller;

import com.example.wrf.demo.entity.SourceTable;
import com.example.wrf.demo.service.CopyDatabaseService;
import com.example.wrf.demo.entity.TargetTable;
import com.example.wrf.demo.repos.SourceTableRepository;
import com.example.wrf.demo.repository.TargetTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/get")
public class DatabaseController {

    @Autowired
    private CopyDatabaseService service;

    @Autowired
    private SourceTableRepository sourceTableRepository;

    @Autowired
    private TargetTableRepository tableRepository;


    @GetMapping("/all")
    public Integer getAll() {
        List<TargetTable> all = tableRepository.findAll();
        return all.size();
    }

    @GetMapping("/save_surce_rest")
    public void saveSourceRest() {
        service.sourceRestTable();
    }

    @GetMapping("/save_all")
    public void saveAll() {
        service.save10000SourceElements();
    }

    @GetMapping("/test")
    public void get() throws InterruptedException {
        service.updateTargetTable();
    }

    @GetMapping("/save")
    public void save(@RequestParam String name, @RequestParam Integer age) {
        SourceTable sourceTable = SourceTable.builder()
                .age(age)
                .name(name)
                .key(UUID.randomUUID().toString())
                .build();
        sourceTableRepository.save(sourceTable);

        /*TargetTable targetTable = TargetTable.builder()
                .firstName("Kirill")
                .yearOld(19)
                .build();
        tableRepository.save(targetTable);*/
    }
}

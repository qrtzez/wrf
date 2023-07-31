package com.example.wrf.demo.controller;

import com.example.wrf.demo.source.SourceTable;
import com.example.wrf.demo.service.CopyDatabaseService;
import com.example.wrf.demo.source.SourceView;
import com.example.wrf.demo.target.TargetTable;
import com.example.wrf.demo.source.SourceTableRepository;
import com.example.wrf.demo.target.TargetTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public List<TargetTable> getAll() {
        List<TargetTable> all = service.getElementsFromTargetTable();
        return all;
    }

    @GetMapping("/save_target_table")
    public void saveTargetTable(@RequestParam int age, @RequestParam String name) throws Exception {
       service.addTableElement(age, name);
    }

    @GetMapping("/save_surce_rest")
    public void saveSourceRest() {
        service.sourceRestTable();
    }

    @GetMapping("/save_all")
    public void saveAll(@RequestParam int count) {
        service.save10000SourceElements(count);
    }

    @GetMapping("/test")
    public void get() throws InterruptedException {
        service.updateTargetTable();
    }

    /*@GetMapping("/save")
    public void save(@RequestParam String name, @RequestParam Integer age) {
        SourceTable sourceTable = SourceTable.builder()
                .age(age)
                .name(name)
                .key(UUID.randomUUID().toString())
                .build();
        sourceTableRepository.save(sourceTable);
*/
        /*TargetTable targetTable = TargetTable.builder()
                .firstName("Kirill")
                .yearOld(19)
                .build();
        tableRepository.save(targetTable);*/
}

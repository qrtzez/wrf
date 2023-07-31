package com.example.wrf.demo.service;

import com.example.wrf.demo.source.SourceRestTable;
import com.example.wrf.demo.source.SourceTable;
import com.example.wrf.demo.target.TargetRestTable;
import com.example.wrf.demo.target.TargetTable;
import com.example.wrf.demo.source.SourceRestTableRepository;
import com.example.wrf.demo.source.SourceTableRepository;
import com.example.wrf.demo.target.TargetRestTableRepository;
import com.example.wrf.demo.target.TargetTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CopyDatabaseService {



    private final TargetTableRepository tableRepository;

    @Autowired
    private SourceTableRepository sourceRepository;

    @Autowired
    private SourceRestTableRepository sourceRestTableRepository;

    @Autowired
    private TargetRestTableRepository targetRestTableRepository;

    public CopyDatabaseService(TargetTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<SourceTable> getElementsFromSourceTable() {
        List<SourceTable> allSource = sourceRepository.findAll();
        return allSource;
    }

    public List<TargetTable> getElementsFromTargetTable() {
        List<TargetTable> all = tableRepository.findAll();
        return all;
    }

    public void addTableElement(int age, String name) throws Exception {
        if (age > 18 ) {
            TargetTable targetTable = TargetTable.builder()
                    .firstName(name)
                    .yearOld(age)
                    .build();
            tableRepository.save(targetTable);
        } else throw new Exception("Пользователю меньше 18 лет");
    }

    @Transactional(transactionManager = "sourceTransactionManager", isolation = Isolation.SERIALIZABLE)
    public void updateTargetTable() {
        List<SourceRestTable> allRest = sourceRestTableRepository.findAll();
        List<TargetRestTable> targetRestTables = allRest.stream()
                .map(rest -> TargetRestTable.builder()
                                .name(rest.getName())
                                .build())
                .collect(Collectors.toList());
        //targetRestTableRepository.saveAll(targetRestTables);

        List<SourceTable> elementsFromSourceTable = getElementsFromSourceTable();


        List<TargetTable> sourceMapTargetElements = elementsFromSourceTable.stream()
                .map(sourceTable -> {
                    TargetTable targetTableEntity = tableRepository.findByKeyP(sourceTable.getKey());
                    if (targetTableEntity == null) {
                        targetTableEntity = TargetTable.builder()
                                .firstName(sourceTable.getName())
                                .yearOld(sourceTable.getAge())
                                .keyP(sourceTable.getKey())
                                .build();
                    } else targetTableEntity.setFirstName(sourceTable.getName());
                    targetTableEntity.setYearOld(sourceTable.getAge());
                    return targetTableEntity;
                })
                .collect(Collectors.toList());
        tableRepository.saveAll(sourceMapTargetElements);
    }

    public void save10000SourceElements(int count) {
        List<SourceRestTable> allRest = sourceRestTableRepository.findAll();
        List<SourceTable> tables = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SourceTable sourceTable = SourceTable.builder()
                    .age(29)
                    .name("Denis")
                    .key(UUID.randomUUID().toString())
                    .restTable(allRest.get(0))
                    .build();
            tables.add(sourceTable);
        }
        sourceRepository.saveAll(tables);
    }

    public void save10000SourceInTargetTableElements(int count) {
        List<TargetTable> toSaveTargetTable = new ArrayList<>();
         for (int i = 0; i < count; i++) {
             TargetTable build = TargetTable.builder()
                     .money(100)
                     .yearOld(19)
                     .firstName("Denis")
                     .keyP("s")
                     .build();
             toSaveTargetTable.add(build);

        }
        tableRepository.saveAll(toSaveTargetTable);
    }


    public void updateTableTarget(String name) throws Exception {
        TargetTable targetTable = tableRepository.findByFirstName(name);
        if (targetTable == null) {
            throw new Exception("Пользователь не найден");
        }
        if (targetTable.getYearOld() != null && targetTable.getYearOld() > 10) {
            targetTable.setYearOld(targetTable.getYearOld() + 1);
            targetTable.setMoney(150);
            tableRepository.save(targetTable);
        } else throw new Exception("Пользователю меньше 10 лет");
    }


    public void sourceRestTable() {
        SourceRestTable rest = SourceRestTable.builder()
                .name("qrtz")
                .build();
        sourceRestTableRepository.save(rest);
    }

}


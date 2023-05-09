package com.example.wrf.demo.service;

import com.example.wrf.demo.entity.SourceRestTable;
import com.example.wrf.demo.entity.SourceTable;
import com.example.wrf.demo.entity.TargetRestTable;
import com.example.wrf.demo.entity.TargetTable;
import com.example.wrf.demo.repos.SourceRestTableRepository;
import com.example.wrf.demo.repos.SourceTableRepository;
import com.example.wrf.demo.repository.TargetRestTableRepository;
import com.example.wrf.demo.repository.TargetTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
public class CopyDatabaseService {


    @Autowired
    private TargetTableRepository tableRepository;

    @Autowired
    private SourceTableRepository sourceRepository;

    @Autowired
    private SourceRestTableRepository sourceRestTableRepository;

    @Autowired
    private TargetRestTableRepository targetRestTableRepository;

    public List<SourceTable> getElementsFromSourceTable() {
        List<SourceTable> allSource = sourceRepository.findAll();
        return allSource;
    }

    //@Transactional(transactionManager = "sourceTransactionManager", isolation = Isolation.SERIALIZABLE)
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
                    TargetTable targetTableEntity = tableRepository.findByKey(sourceTable.getKey());
                    if (targetTableEntity == null) {
                        targetTableEntity = TargetTable.builder()
                                .firstName(sourceTable.getName())
                                .yearOld(sourceTable.getAge())
                                .key(sourceTable.getKey())
                                .restTable(targetRestTables.get(0))
                                .build();
                    } else targetTableEntity.setFirstName(sourceTable.getName());
                    targetTableEntity.setYearOld(sourceTable.getAge());
                    targetTableEntity.setRestTable(targetRestTables.get(0));
                    return targetTableEntity;
                })
                .collect(Collectors.toList());
        tableRepository.saveAll(sourceMapTargetElements);
    }

    public void save10000SourceElements() {
        List<SourceRestTable> allRest = sourceRestTableRepository.findAll();
        List<SourceTable> tables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
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

    public void sourceRestTable() {
        SourceRestTable rest = SourceRestTable.builder()
                .name("qrtz")
                .build();
        sourceRestTableRepository.save(rest);
    }

}


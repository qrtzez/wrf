package com.example.wrf.demo.service.shedule;

import com.example.wrf.demo.service.CopyDatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class SheduleCopyDatabase {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    private CopyDatabaseService databaseService;

    //@Scheduled(fixedDelay = 30000L)
    public void sheduledDatabase() {
        executor.execute(() -> databaseService.updateTargetTable());
        log.info("База данных успешно скопированна! Работающий поток " + Thread.currentThread().getName());
    }
}

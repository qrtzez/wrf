package com.example.wrf.demo;

import com.example.wrf.demo.target.TargetTable;
import com.example.wrf.demo.target.TargetTableRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TargetTableTest {

    @Autowired
    private TargetTableRepository tableRepository;


    @AfterEach
    void tearDown() {
        tableRepository.deleteAll();
    }

    @Test
    void selectAllTargetTable() {
        //given
        TargetTable kirillTarget = TargetTable.builder()
                .firstName("Kirill")
                .keyP("123-321")
                .money(100)
                .yearOld(20)
                .build();

        tableRepository.save(kirillTarget);

        //when
        TargetTable byKey = tableRepository.findByKeyP("123-321");

        //then
        assertThat(byKey).isNotNull();
        assertThat(byKey).isExactlyInstanceOf(TargetTable.class);
        assertThat(byKey.getFirstName()).isEqualTo("Kirill");
    }
}

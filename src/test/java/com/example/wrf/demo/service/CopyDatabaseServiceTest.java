package com.example.wrf.demo.service;

import com.example.wrf.demo.target.TargetTable;
import com.example.wrf.demo.target.TargetTableRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CopyDatabaseServiceTest {

    @Mock
    private TargetTableRepository tableRepository;

    private CopyDatabaseService copyDatabaseService;

    @Captor
    ArgumentCaptor<TargetTable> argumentCaptor;

    @Captor
    ArgumentCaptor<List<TargetTable>> listArgumentCaptor;

    @BeforeEach
    void setUp() {
        copyDatabaseService = new CopyDatabaseService(tableRepository);
    }

    @Test
    void getAllElements() {
        //when
        copyDatabaseService.getElementsFromTargetTable();

        //then
        verify(tableRepository).findAll();
    }

    @SneakyThrows
    @Test
    void addTableElement() {
        //given
        int age = 19;
        String name = "Denis";

        //when
        copyDatabaseService.addTableElement(age, name);

        //then
        verify(tableRepository).save(argumentCaptor.capture());
        TargetTable argumentCaptorValue = argumentCaptor.getValue();
        assertThat(argumentCaptorValue).isNotNull();
        assertThat(argumentCaptorValue.getFirstName()).isEqualTo(name);
        assertThat(argumentCaptorValue.getYearOld()).isEqualTo(age);
        verify(tableRepository, times(1)).save(any());

    }

    @Test
    void addTableElementWithException() {
        //given
        int age = 17;
        String name = "Denis";

        //when
        //than
        assertThatThrownBy(() -> copyDatabaseService.addTableElement(age, name))
                .isInstanceOf(Exception.class)
        .hasMessageContaining("Пользователю меньше 18 лет");

        verify(tableRepository, never()).save(any());

    }

    @Test
    void save10000SourceElements() {
        //given
        int count = 20;

        //when
        copyDatabaseService.save10000SourceInTargetTableElements(count);

        //then
        verify(tableRepository).saveAll(listArgumentCaptor.capture());
        List<TargetTable> allValues = listArgumentCaptor.getValue();
        TargetTable capture = allValues.get(0);
        assertThat(allValues).isNotNull();
        assertThat(allValues.size()).isEqualTo(20);
        assertThat(capture.getYearOld()).isEqualTo(19);
        assertThat(capture.getFirstName()).isEqualTo("Denis");
        assertThat(capture.getKeyP()).isEqualTo("s");
        verify(tableRepository, times(1)).saveAll(allValues);



    }

    @Test
    void updateTableTarget() throws Exception {
        //given
        String firstName = "ztrq";
        Integer money = 100;
        Integer oldYear = 9;

        TargetTable targetTableToSave = TargetTable.builder()
                .firstName(firstName)
                .yearOld(oldYear)
                .money(money)
                .build();

        //when
        when(tableRepository.findByFirstName(any(String.class))).thenReturn(targetTableToSave);
        copyDatabaseService.updateTableTarget(firstName);

        //then
        verify(tableRepository).save(argumentCaptor.capture());
        verify(tableRepository, times(1)).save(any(TargetTable.class));
        TargetTable captureTargetTable = argumentCaptor.getValue();
        assertThat(captureTargetTable).isNotNull();
        assertThat(captureTargetTable.getFirstName()).isEqualTo(firstName);
        assertThat(captureTargetTable.getYearOld()).isEqualTo(18);
        assertThat(captureTargetTable.getMoney()).isEqualTo(150);
    }

    @SneakyThrows
    @Test
    public void  failUpdateTargetTable() {
        //given
        String firstName = "ztrq";
        Integer money = 100;
        Integer oldYear = 9;

        TargetTable targetTableToSave = TargetTable.builder()
                .firstName(firstName)
                .yearOld(oldYear)
                .money(money)
                .build();

        //when
        //then
        when(tableRepository.findByFirstName(any(String.class))).thenReturn(targetTableToSave);
        assertThatThrownBy(() -> copyDatabaseService.updateTableTarget(firstName))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Пользователю меньше 10 лет");
    }
}
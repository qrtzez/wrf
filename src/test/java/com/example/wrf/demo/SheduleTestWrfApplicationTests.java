package com.example.wrf.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SheduleTestWrfApplicationTests {

    Calculator calculatorInit = new Calculator();

    @Test
    void contextLoads() {
    }

    @Test
    void calculatorAddTest() {
        //given
        int a = 20;
        int b = 10;

        //when
        int result = calculatorInit.add(a, b);

        //then
        assertThat(result).isEqualTo(30);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }

}

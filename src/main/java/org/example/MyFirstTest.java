package org.example;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstTest {



    @Test
    public void firstTest(){
        String date = "20.09.2023";
        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(parseDate.getDayOfWeek(), DayOfWeek.FRIDAY );
    }

    @Test
    public void secondTest(){
        String date = "20.09.2023";
        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(parseDate.getDayOfWeek(), DayOfWeek.FRIDAY );
    }
}

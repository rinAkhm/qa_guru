package org.example;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstTest {
private String date = "20.09.2033";
    
    @Test
    public void firstTest(){
        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(parseDate.getDayOfWeek(), DayOfWeek.FRIDAY);
    }

    @Test
    public void secondTest(){
        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(parseDate.getDayOfWeek(), DayOfWeek.SUNDAY);
    }
}

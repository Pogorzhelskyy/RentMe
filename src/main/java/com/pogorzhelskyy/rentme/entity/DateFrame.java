package com.pogorzhelskyy.rentme.entity;

import java.time.LocalDate;


public class DateFrame {
    public LocalDate from;
    public LocalDate until;

    public DateFrame(LocalDate from, LocalDate until) {
        this.from = from;
        this.until = until;
    }
}

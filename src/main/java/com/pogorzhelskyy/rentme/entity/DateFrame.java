package com.pogorzhelskyy.rentme.entity;

import java.util.Date;

public class DateFrame {
    public Date from;
    public Date until;

    public DateFrame(Date from, Date until) {
        this.from = from;
        this.until = until;
    }
}

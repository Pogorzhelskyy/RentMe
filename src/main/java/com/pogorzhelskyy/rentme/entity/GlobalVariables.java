package com.pogorzhelskyy.rentme.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalVariables {
    @Value("${app.currency}")
    public static String currency;
}

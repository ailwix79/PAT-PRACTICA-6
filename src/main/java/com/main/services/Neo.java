package com.main.services;

import com.main.utilities.StartEndDate;

import org.springframework.stereotype.Service;

@Service
public interface Neo {
    String getNeoInfo();
    String getNeoInfoDates(StartEndDate startEndDate);
}


package com.main.controller;

import org.springframework.web.bind.annotation.RestController;

import com.main.services.Apod;
import com.main.services.Neo;
import com.main.utilities.StartEndDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

@RestController
public class AppController {

    @Autowired
    private Neo neo;

    @Autowired
    private Apod apod;

    @GetMapping("/neo")
    public @ResponseBody String getNeo() {
        return neo.getNeoInfo();
    }

    @GetMapping("/apod")
    public @ResponseBody String getApod() {
        return apod.getApodInfo();
    }

    @PostMapping(path="/neoDates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String dateFinder(@RequestBody StartEndDate startEndDate) {
        return neo.getNeoInfoDates(startEndDate);
    }
}



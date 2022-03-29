package com.main.services;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.main.utilities.StartEndDate;

@Service
public class NeoImp implements Neo {
    private String key = "mxBbZ4QkKet4IjgxUTOcJ38uGcoDGBwaKzS7OYcM";
    Logger log = LoggerFactory.getLogger(NeoImp.class);

    public String getNeoInfo() {
        log.info("Accesing the Neo API right now!");
        log.info("No dates have been selected, obtaining the most recent data");

        String url = "https://api.nasa.gov/neo/rest/v1/feed?api_key="+key;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        log.info("Data is ready!");

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
    }
    
    public String getNeoInfoDates(StartEndDate startEndDate) {
        log.info("Accesing the Neo API right now!");
        log.info("Dates Selected: Start Date:  " + startEndDate.getStartDate() + "  End Date:  " + startEndDate.getEndDate());

        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+startEndDate.getStartDate()+"&end_date="+startEndDate.getEndDate()+"&api_key="+key;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        
        log.info("Data is ready!");

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
    }
}

package com.main.services;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApodImp implements Apod {
    private String key = "mxBbZ4QkKet4IjgxUTOcJ38uGcoDGBwaKzS7OYcM";
    Logger log = LoggerFactory.getLogger(NeoImp.class);

    public String getApodInfo() {
        log.info("Accesing the Apod API right now!");

        String url = "https://api.nasa.gov/planetary/apod?api_key="+key;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        log.info("Data is ready!");
        
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
    }
}

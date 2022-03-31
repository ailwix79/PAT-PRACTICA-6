package com.main.practica5.services;

import java.util.Iterator;
import java.util.List;

import com.main.services.Apod;
import com.main.services.ApodImp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class ApodImpTest {
    
    final Apod apod = new ApodImp();
    
    @Test
    public void apodServiceReturnsCorrectInformation() {
        String result = apod.getApodInfo();
        List<String> expected = List.of("date","explanation","hdurl","media_type","service_version","title","url");

        Iterator<String> e = expected.iterator();

        while(e.hasNext()) {
            then(result).contains(e.next());
        }
    }
}

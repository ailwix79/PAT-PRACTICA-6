package com.main.practica5.services;

import java.util.Iterator;
import java.util.List;

import com.main.services.Neo;
import com.main.services.NeoImp;
import com.main.utilities.StartEndDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class NeoImpTest {
    
    final Neo neo = new NeoImp();
    
    @Test
    public void neoServiceReturnsCorrectInformation() {
        String result = neo.getNeoInfo();
        List<String> expected = List.of("links","element_count","near_earth_objects");
    
        Iterator<String> e = expected.iterator();
    
        while(e.hasNext()) {
            then(result).contains(e.next());
        }
    }

    @Test
    public void neoServiceReturnsTheInformationOfTheDatesSpecifiedCorrectly() {
        StartEndDate dates = new StartEndDate("2020-04-01","2020-04-05");

        String result = neo.getNeoInfoDates(dates);
        List<String> expected = List.of("links","element_count","near_earth_objects","2020-04-01","2020-04-02","2020-04-03","2020-04-04","2020-04-05");
    
        Iterator<String> e = expected.iterator();
    
        while(e.hasNext()) {
            then(result).contains(e.next());
        }
    }
}

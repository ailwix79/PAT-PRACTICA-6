package com.icai.practicas.model;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class TelefonoTest {

    private static final ArrayList<String> NUMS = new ArrayList<String>(Arrays.asList( 
            "(06442) 3933023",
            "(02852) 5996-0",
            "(042) 1818 87 9919",
            "06442 / 3893023",
            "06442 / 38 93 02 3",
            "06442/3839023",
            "042/ 88 17 890 0",
            "+49 221 549144 – 79",
            "+49 221 – 542194 79",
            "+49 (221) – 542944 79",
            "0 52 22 – 9 50 93 10"));

    @Test
    public void method_behaves_as_expected() {
        Iterator<String> it = NUMS.iterator();
        
        while(it.hasNext()){
            then((new DNI(it.next())).validar()).isFalse();
        }
    }
}

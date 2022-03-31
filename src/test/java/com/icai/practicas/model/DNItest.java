package com.icai.practicas.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class DNItest {

    private static final ArrayList<String> INVALIDOS = new ArrayList<String>(Arrays.asList(
            "00000000T", 
            "00000001R", 
            "99999999R"));

    private static final ArrayList<String> NOSTRUCT = new ArrayList<String>(Arrays.asList(
            "48A76545547634N",
            "44135\"453634ASDFA=)(&704S",
            "971J",
            "9",
            "7299**?¿?Ñ8616C",
            "54215$\"5234SRFE082B",
            "028$%/·$$5E",
            "7552940ASD4A",
            "326017SDS47Y",
            "04D-,*S052660Z",
            "594120772342AH",
            "7890J878P",
            "-3000501Z",
            "0299454HZ",
            "661=0880H",
            "8670R",
            "309asd335M",
            "P39442084M",
            "J",
            "67285324AD162L",
            "32SD722432143214321SADAF7240B"));

    private static final ArrayList<String> NOCTRLDIGIT = new ArrayList<String>(Arrays.asList(
            "00100020I", 
            "00010001O", 
            "99999239I",
            "66170880O",
            "83746245I",
            "87886584O",
            "22657875I",
            "47425863I",
            "70600336O"));

    @Test
    public void method_performance_test() {

        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/testDNI.txt")))) {
            while((str = reader.readLine())!= null){
                then((new DNI(str)).validar()).isTrue();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void method_detects_invalid_dni() {
        Iterator<String> it = INVALIDOS.iterator();
        
        while(it.hasNext()){
            then((new DNI(it.next())).validar()).isFalse();
        }
    }

    @Test
    public void method_detects_incorrect_structure() {
        Iterator<String> it = NOSTRUCT.iterator();
        
        while(it.hasNext()){
            then((new DNI(it.next())).validar()).isFalse();
        }
    }

    @Test
    public void method_detects_incorrect_control_digit() {
        Iterator<String> it = NOCTRLDIGIT.iterator();
        
        while(it.hasNext()){
            then((new DNI(it.next())).validar()).isFalse();
        }
    }
}

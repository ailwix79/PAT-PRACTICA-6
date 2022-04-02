package com.icai.practicas.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

public class DNITest {

    @Test
    public void method_performance_test_with_valid_data() {

        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/data/validDNIs.txt")))) {
            while((str = reader.readLine())!= null){
                then((new DNI(str)).validar()).isTrue();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void method_returns_false_when_detects_invalid_dni() {
        
        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/data/invalidDNIs.txt")))) {
            while((str = reader.readLine())!= null){
                then((new DNI(str)).validar()).isFalse();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void method_returns_false_when_detects_incorrect_structure() {

        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/data/invalidStruct.txt")))) {
            while((str = reader.readLine())!= null){
                then((new DNI(str)).validar()).isFalse();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void method_returns_false_when_detects_incorrect_control_digit() {

        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/data/invalidCtrlLetter.txt")))) {
            while((str = reader.readLine())!= null){
                then((new DNI(str)).validar()).isFalse();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

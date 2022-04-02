package com.icai.practicas.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

public class TelefonoTest {

    @Test
    public void method_behaves_as_expected() {

        String str;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/icai/practicas/model/data/invalidPhones.txt")))) {
            while((str = reader.readLine())!= null){
                then((new Telefono(str)).validar()).isFalse();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.icai.practicas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    private static final String expectedMessage1 =
            """
            <!doctype>
            <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Response 1</title>
                </head>
                <body>
                    <h1>Muchas gracias por enviar los datos</h1>
                    <a href="/">Volver</a>
                </body>
            </html>
            """;

    private static final String expectedMessage2 =
            """
            <!doctype>
            <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Response 1</title>
                </head>
                <body>
                    <h1>Hemos tenido un problema con su solicitud.</h1>
                    <p>Revise los datos introducidos</p>
                    <a href="javascript:history.back()">Volver</a>
                </body>
            </html>
            """;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProcessController processController;

    @Test
    public void app_responds_correctly_when_given_correct_dataset() {

        String address = "http://localhost:" + port + "/api/v1/process-step1";

        HashMap<ProcessController.DataRequest,HttpStatus> dataFirstTest = new HashMap<ProcessController.DataRequest, HttpStatus>();
        dataFirstTest.put(new ProcessController.DataRequest("Jose Maria", "06793079J", "640789432"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest(null, "06793079J", "640789432"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("Jose Maria", "", "640789432131232"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("Jose Maria", "06793079J", ""), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("J0s# M@riÑ{", "06793079AJ", "640789A432"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("Jose Maria", "06793079J", "SDSGRJERG"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("**565vwwet", "06793079J", "640789432"), HttpStatus.OK);
        dataFirstTest.put(new ProcessController.DataRequest("Jose €#~#@=/", "", "·$%·$·$%)/(/\""), HttpStatus.OK);
        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (Map.Entry<ProcessController.DataRequest,HttpStatus> data:
             dataFirstTest.entrySet()) {
                HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(data.getKey(), headers);
                ResponseEntity<ProcessController.DataResponse> result = this.restTemplate.postForEntity(address, request, ProcessController.DataResponse.class);
                then(result.getStatusCode()).isEqualTo(data.getValue());
                then((result.getBody())).isEqualTo(null);       // Method returns null body
        }
    }

    @Test
    public void app_responds_correctly_when_given_correct_dataset_legacy() {

        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        MultiValueMap<String, String> datos = new LinkedMultiValueMap<>();
        datos.add("fullName", "Jose Maria");
        datos.add("dni", "55476398J");
        datos.add("telefono", "646534345");

        HashMap<MultiValueMap<String, String>,HttpStatus> dataSecondTest = new HashMap<MultiValueMap<String, String>, HttpStatus>();
        dataSecondTest.put(datos, HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        for (Map.Entry<MultiValueMap<String, String>,HttpStatus> data:
            dataSecondTest.entrySet()) {
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data.getKey(), headers);
                ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
                then(result.getStatusCode()).isEqualTo(data.getValue());
                then((result.getBody())).isEqualTo(null);       // Method returns null body
        }
    }
}

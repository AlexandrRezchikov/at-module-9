package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.config;

public class Specifications {

    public static RequestSpecification requestSpec(String uri) {

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addFilter(new AllureRestAssured())
                .setConfig(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void installSpecification(RequestSpecification request) {

        RestAssured.requestSpecification = request;
    }
}

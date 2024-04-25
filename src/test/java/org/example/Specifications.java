package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.config;

public class Specifications {

    public static RequestSpecification requestSpec(String uri) {

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .setConfig(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {

        return new ResponseSpecBuilder()
                .expectStatusCode(Matchers.is(statusCode))
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {

        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}

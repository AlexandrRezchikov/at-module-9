package org.example.ApiTests;

import org.example.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.Endpoints.DELETE_USER;
import static org.hamcrest.Matchers.emptyString;

public class ApiTestsDelete extends BaseTest {

    @Test(description = "delete /api/users/2, statusCode is 204")
    public void deleteUser() {

        given()
                .when()
                .delete(DELETE_USER)
                .then()
                .log().all()
                .statusCode(204)
                .body(emptyString());
    }
}

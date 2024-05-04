package org.example.ApiTests;

import org.example.BaseTest;
import org.example.pojo.User;
import org.example.pojo.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.Endpoints.PUT_UPDATE_USER;
import static org.example.utils.ConfigReader.*;

public class ApiTestsPut extends BaseTest {

    @Test(description = "put /api/users/2, statusCode is 200")
    public void putUpdateUser() {
        User user = new User(NAME, JOB_UPDATE);

        UserResponse userResponse = given()
                .body(user)
                .when()
                .put(PUT_UPDATE_USER)
                .then().log().all()
                .statusCode(200)
                .extract().body().as(UserResponse.class);

        Assert.assertEquals(user.getName(), userResponse.getName());
        Assert.assertEquals(user.getJob(), userResponse.getJob());
        Assert.assertNotNull(userResponse.getUpdatedAt());
    }
}

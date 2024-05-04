package org.example.ApiTests;

import org.example.BaseTest;
import org.example.pojo.User;
import org.example.pojo.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.Endpoints.POST_CREATE_USER;
import static org.example.utils.ConfigReader.*;

public class ApiTestsPost extends BaseTest {

    @Test(description = "post /users, statusCode is 201")
    public void postCreateUser() {
        User user = new User(NAME, JOB);

        UserResponse userResponse = given()
                .body(user)
                .when()
                .post(POST_CREATE_USER)
                .then().log().all()
                .statusCode(201)
                .extract().body().as(UserResponse.class);

        Assert.assertEquals(user.getName(), userResponse.getName());
        Assert.assertEquals(user.getJob(), userResponse.getJob());
        Assert.assertNotNull(userResponse.getId());
        Assert.assertNotNull(userResponse.getCreatedAt());
    }
}

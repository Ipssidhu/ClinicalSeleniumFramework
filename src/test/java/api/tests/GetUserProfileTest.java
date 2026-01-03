package api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.base.ApiBaseTest;
import io.restassured.response.Response;

public class GetUserProfileTest extends ApiBaseTest {

    @Test
    public void getProfile_shouldReturnUserDetails() {

        Response response =
                given()
                    .spec(requestSpec)
                .when()
                    .get("/auth/me");

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("username"));

        System.out.println("Username: " + response.jsonPath().getString("username"));
    }
}

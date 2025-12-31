package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTokenTest {

    @Test
    public void loginAndAccessProtectedApi() {

        // 1️⃣ Set base URI
        RestAssured.baseURI = "https://dummyjson.com";

        // 2️⃣ Login request body
        String loginPayload = "{\n" +
                "  \"username\": \"emilys\",\n" +
                "  \"password\": \"emilyspass\"\n" +
                "}";

        // 3️⃣ Send LOGIN request
        Response loginResponse =
                RestAssured
                    .given()
                        .header("Content-Type", "application/json")
                        .body(loginPayload)
                    .when()
                        .post("/auth/login")
                    .then()
                        .statusCode(200)
                        .extract()
                        .response();

        // 4️⃣ Extract access token
        String accessToken = loginResponse.jsonPath().getString("accessToken");
        System.out.println("Access Token: " + accessToken);

        Assert.assertNotNull(accessToken, "Access token should not be null");

        // 5️⃣ Call secured API using token
        Response meResponse =
                RestAssured
                    .given()
                        .header("Authorization", "Bearer " + accessToken)
                    .when()
                        .get("/auth/me")
                    .then()
                        .statusCode(200)
                        .extract()
                        .response();

        // 6️⃣ Validate secured API response
        String username = meResponse.jsonPath().getString("username");
        System.out.println("Logged-in Username: " + username);

        Assert.assertEquals(username, "emilys");
    }
}

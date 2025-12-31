package api;

import org.testng.annotations.Test;

import api.auth.TokenManager;

import static io.restassured.RestAssured.given;

public class GetProfileTest {

    @Test
    public void getUserProfile_withValidToken() {

        given()
            .baseUri("https://dummyjson.com")
            .header("Authorization", "Bearer " + TokenManager.getAccessToken())
        .when()
            .get("/auth/me")
        .then()
            .statusCode(200);

        System.out.println("✅ /auth/me accessed successfully with token");
    }
}

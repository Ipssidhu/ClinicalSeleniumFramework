package api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import api.auth.TokenManager;

public class ApiClient {

    static {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    public static Response get(String endpoint) {
        return given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
    }
}

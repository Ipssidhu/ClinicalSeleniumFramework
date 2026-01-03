package api.auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {
	
	private static String accessToken;
	
	public static String getAccessToken() {

	    if (accessToken == null) {

	        Response response =
	            given()
	                .contentType("application/json")
	                .body("{ \"username\": \"emilys\", \"password\": \"emilyspass\" }")
	            .when()
	                .post("/auth/login");

	        int statusCode = response.getStatusCode();

	        if (statusCode != 200) {
	            System.out.println("⚠ Login API failed in setup. Status: " + statusCode);
	            System.out.println(response.asPrettyString());
	            throw new RuntimeException("Token generation failed. Cannot proceed.");
	        }

	        accessToken = response.jsonPath().getString("accessToken");
	        System.out.println("Token generated successfully");
	    }

	    return accessToken;
	}


}

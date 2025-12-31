package api.auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {
	
	private static String accessToken;
	
	public static String getAccessToken() {
		
		if(accessToken== null) {
			RestAssured.baseURI="https://dummyjson.com";
			
			Response response =
					given()
					.header("Content-Type", "application/json")
                    .body("{ \"username\": \"emilys\", \"password\": \"emilyspass\" }")
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
			
			accessToken= response.jsonPath().getString("accessToken");
			System.out.println(" Token Genenrated Successfully");
		}
		return accessToken;
	}

}

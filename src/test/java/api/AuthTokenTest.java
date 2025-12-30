package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AuthTokenTest {

    @Test
    public void generateAuthToken() {

      /*  RestAssured.baseURI = "https://reqres.in";

        String requestBody = "{\n" +
                "  \"email\": \"eve.holt@reqres.in\",\n" +
                "  \"password\": \"cityslicka\"\n" +
                "}";

        Response response =
                given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        String token = response.jsonPath().getString("token");

        System.out.println("Generated Token: " + token);  */
    	
    	String token =
    			given()
    			    .contentType("application/json")
    			    .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
    			.when()
    			    .post("https://reqres.in/api/login")
    			.then()
    			    .statusCode(200)
    			    .extract()
    			    .path("token");

    			System.out.println("Token: " + token);
    }
}

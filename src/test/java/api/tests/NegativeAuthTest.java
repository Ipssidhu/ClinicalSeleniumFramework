package api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class NegativeAuthTest {

    @Test
    public void getProfile_withoutToken_shouldReturn401() {

        RestAssured.baseURI = "https://dummyjson.com";

        Response response =
                given()
                    .contentType("application/json")
                .when()
                    .get("/auth/me");

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println("Response (No Token):");
        System.out.println(response.asPrettyString());
    }
    
    @Test
     public void getProfile_withoutToken_shouldReturn403() {
    	RestAssured.baseURI = "https://dummyjson.com";

        Response response =
                given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer invalid_token_123")
                .when()
                    .get("/auth/me");

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println("Response (Invalid Token):");
        System.out.println(response.asPrettyString());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

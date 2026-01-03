package api.base;

import org.testng.annotations.BeforeClass;

import api.auth.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiBaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setupApiSpec() {

        // Base URI for all API calls
        RestAssured.baseURI = "https://dummyjson.com";

        // Build reusable RequestSpecification
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Authorization", "Bearer " + TokenManager.getAccessToken())
                .build();
    }
}

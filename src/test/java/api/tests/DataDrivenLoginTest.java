package api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DataDrivenLoginTest {

    @DataProvider(name = "loginData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {"emilys", "emilyspass", 200},
                {"emilys", "wrongpass", 400},
                {"wronguser", "emilyspass", 400}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginApi_shouldReturnExpectedStatus(String username,
                                                     String password,
                                                     int expectedStatusCode) {

        RestAssured.baseURI = "https://dummyjson.com";

        String requestBody = "{\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response =
                given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post("/auth/login");

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

        System.out.println("Username: " + username);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println(response.asPrettyString());
    }
}

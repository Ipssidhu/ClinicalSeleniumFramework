package api.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import api.client.ApiClient;
import io.restassured.response.Response;

public class GetUserProfileTest {

    @Test
    public void getProfile_shouldReturnUserDetails() {

        ApiClient client = new ApiClient();

        Response response = client.get("/auth/me");

        // Assertions
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("username"));

        System.out.println("Username: " + response.jsonPath().getString("username"));
    }
}

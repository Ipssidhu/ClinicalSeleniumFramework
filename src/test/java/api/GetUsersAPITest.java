package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetUsersAPITest {

	/*
    @Test
    public void getUsers_shouldReturn200() {

        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request
        Response response =
                RestAssured
                        .given()
                        .when()
                        .get("/users");

        // Print response (learning purpose)
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
    } */
	
	@Test
	public void validateUserResponse() {
		given()
		.when()
		 .get("https://jsonplaceholder.typicode.com/users")
		 .then()
		 //Source code validation
		    .statusCode(200)
		 /*	
		 	.body("id[0]",equalTo(1))
		 	.body("name[0]", notNullValue())
		 // Print response
          /  .log().all();
		 */
		    
		    // First user validations
            .body("[0].id", equalTo(1))
            .body("[0].name", equalTo("Leanne Graham"))

            // Second user email
            .body("[1].email", equalTo("Shanna@melissa.tv"))

            // Nested JSON validation
            .body("[0].address.city", equalTo("Gwenborough"));
	}
}

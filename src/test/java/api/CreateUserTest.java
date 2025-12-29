package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class CreateUserTest {

    @Test
    public void createUser_postRequest() {

        String requestBody = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"job\": \"QA\"\n" +
                "}";

        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
            .body(requestBody)

        .when()
            .post("/users")

        .then()
            .statusCode(201)
            .body("name", equalTo("John"))
            .body("job", equalTo("QA"))
            .body("id", notNullValue());
    }

}

package api.rest.tests;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RegisterUser {
    @Test
    public void testRegisterANewUserWithSuccess() {
        // Configure the Rest API access path
        baseURI = "https://reqres.in/api";

        // Register a new user
        given()
                .body("{\n" +
                        "  \"username\": \"Nicolli\",\n" +
                        "  \"email\": \"nic@teste.com\",\n" +
                        "  \"password\": \"teste123\"\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
            .post("/register")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("token", not(isEmptyString()));
    }

    @Test
    public void testInsertInvalidEmailWhenRegisteringANewUser() {
        // Insert invalid email when registering new user
        given()
                .body("{\n" +
                        "  \"username\": \"Nicolli\",\n" +
                        "  \"email\": \"nic@teste\",\n" +
                        "  \"password\": \"teste123\"\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post("/register")
        .then()
                .statusCode(400)
                .body("error", not(isEmptyString()));
    }

}

package com.apirest.tests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RegisterUser {
    @Test
    public void testRegisterANewUserWithSuccess() {
        // Configure the Rest API access path
        baseURI = "https://reqres.in/api";
        UserPOJO.User newUser = new UserPOJO.User("Nicolli", "nic@teste.com", "senha123");

        // Register a new user
        given()
                .body(newUser)
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
        // Configure the Rest API access path
        baseURI = "https://reqres.in/api";
        UserPOJO.User newUser = new UserPOJO.User("Nicolli", "nic@teste", "senha123");

        // Insert invalid email when registering new user
        given()
                .body(newUser)
                .contentType(ContentType.JSON)
        .when()
                .post("/register")
        .then()
                .statusCode(400)
                .body("error", not(isEmptyString()));
    }

}

package com.apirest.tests;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ListUsersTest {
    @Test
    public void testGetUsersPerPage() {
        // Configurar o caminho de acesso a API Rest
        baseURI = "https://reqres.in/api";

        // Listar usuários por página
        given()
                .param("page", 1)
                .param("per_page", 6)
            .when()
                .get("/users")
            .then()
                .statusCode(200)
                .body("total", equalTo(12))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total_pages", equalTo(2))
                .body("data.size()", equalTo(6));
    }

    @Test
    public void testNotFoundPage() {
        // Solicitar página inexistente
        given()
                .param("page", 3)
                .param("per_page", 6)
            .when()
                .get("/users")
            .then()
                .statusCode(200)
            .body("data.size()", equalTo(0))
                .body("total_pages", equalTo(2));
    }
}
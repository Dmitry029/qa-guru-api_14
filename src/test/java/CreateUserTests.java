import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateUserTests {

    @Test
    @DisplayName("Позитивный тест. Создать пользователя")
    void createUserTest() {
        String userData = "{\"name\": \"Sting\", \"job\": \"singer\"}";

        given()
                .body(userData)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Sting"));
    }

    @Test
    @DisplayName("Негативный тест. Создать пользователя без указания имени")
    void createUserTestWithoutName() {
        String userData = "{\"name\", \"job\": \"singer\"}";

        given()
                .body(userData)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

}



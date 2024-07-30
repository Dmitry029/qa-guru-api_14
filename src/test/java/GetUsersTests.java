import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUsersTests {

    @Test
    @DisplayName("Получить размер списка всех пользователей")
    void getSizeOfAllUsersList() {
        int allUsers = 12;
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(allUsers));
    }

    @Test
    @DisplayName("Получить размер списка пользователей на странице")
    void getUsersListSizeOnThePage() {
        int usersListSize = 6;
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(usersListSize));
    }

    @Test
    @DisplayName("Сравнить ожидаемый список Id с полученным после запроса")
    void compareExpectedListOfIdsAndActual() {
        List<Integer> expectedId = List.of(1, 2, 3, 4, 5, 6);
        Response response = given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();
        List<Integer> actualId = response.jsonPath().getList("data.id");
        assertEquals(expectedId, actualId);
    }
}
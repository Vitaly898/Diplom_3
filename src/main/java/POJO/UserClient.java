package POJO;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;

public class UserClient {
    @Step("Регистрация нового пользователя")
    public static Response createNewUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("Логин корректного пользователя.")
    public static ValidatableResponse loginUser(User user) {
        return  given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }
    @Step
    public static ValidatableResponse deleteUser(String accessToken){
        return given()
                .header("Authorization",accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }

}

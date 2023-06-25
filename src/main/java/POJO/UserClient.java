package POJO;

import io.qameta.allure.Step;
import POJO.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
public class UserClient {
    @Step("Регистрация нового пользователя")
    public static Response postCreateNewUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("Логин корректного пользователя.")
    public static Response checkRequestAuthLogin(User user) {
        return given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");
    }
    @Step
    public static Response deleteUser(String accessToken){
        return given()
                .header("Authorization",accessToken)
                .when()
                .delete("/api/auth/user");
    }

}

package user.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import user.models.User;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public final String ROOT = "/api/auth/register";


    @Step("create user")
    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(ROOT)
                .then();
    }
}

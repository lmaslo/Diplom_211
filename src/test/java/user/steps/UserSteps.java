package user.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import user.models.User;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class UserSteps {

    public final String ROOT = "/api/auth";

    @Step("Create user")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(request)
                .body(user)
                .when()
                .post(ROOT + "/register")
                .then();
    }

   /* @Step("Delete user")
    public ValidatableResponse deleteUser() {
        return given()
                .spec(request)
                .when()
                .delete(ROOT+"/user")
                .then();
    }*/
}

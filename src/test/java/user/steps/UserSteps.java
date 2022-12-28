package user.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import user.models.User;
import user.models.UserCreate;
import user.models.UserData;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class UserSteps {

    public final String ROOT = "/api/auth";

    @Step("Create user")
    public ValidatableResponse createUser(UserCreate user) {
        return given()
                .spec(request)
                .body(user)
                .when()
                .post(ROOT + "/register")
                .then();
    }


    @Step("Auth user")
    public ValidatableResponse authUser(User user) {
        return given()
                .spec(request)
                .body(user)
                .when()
                .post(ROOT + "/login")
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(request)
                .header("Authorization", accessToken)
                .when()
                .delete(ROOT+"/user")
                .then();
    }

    @Step("Edit user name")
    public ValidatableResponse editUser(String accessToken, UserData user) {
        return given()
                .spec(request)
                .header("Authorization", accessToken)
                .body(user)
                .when()
                .patch(ROOT+"/user")
                .then();
    }

}

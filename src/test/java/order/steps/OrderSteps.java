package order.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import user.models.UserCreate;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class OrderSteps {

    public final String ROOT = "/api/orders";

    @Step("Get all orders")
    public ValidatableResponse getAllOrdersWithOutAuth() {
        return given()
                .spec(request)
                .when()
                .get(ROOT + "/all")
                .then();
    }

    @Step("Get orders for user")
    public ValidatableResponse getOrdersWithAuth(String accessToken) {
        return given()
                .spec(request)
                .header("Authorization", accessToken)
                .when()
                .get(ROOT )
                .then();
    }


}

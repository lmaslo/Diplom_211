package order.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import user.models.UserCreate;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class OrderSteps {

    public final String ROOT = "/api/orders";

    @Step("Get all orders")
    public ValidatableResponse getAllOrders() {
        return given()
                .spec(request)
                .when()
                .get(ROOT + "/all")
                .then();
    }


}

package order.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import order.models.Order;

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
                .get(ROOT)
                .then();
    }

    @Step("Create order without ingredients")
    public ValidatableResponse createOrderWithOutIngredients() {
        return given()
                .spec(request)
                .when()
                .post(ROOT)
                .then();
    }

    @Step("Create order")
    public ValidatableResponse createOrder(String accessToken, Order order) {
        return given()
                .spec(request)
                .header("Authorization", accessToken)
                .body(order)
                .log().all()
                .when()
                .post(ROOT)
                .then();
    }

}

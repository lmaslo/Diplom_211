package order.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import order.models.Order;
import order.steps.OrderSteps;
import org.junit.After;
import org.junit.Test;
import user.models.UserCreate;
import user.models.UserCreateResponse;
import user.models.UserGeneration;
import user.steps.UserSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTests {

    OrderSteps step = new OrderSteps();
    UserSteps stepUser = new UserSteps();
    UserGeneration generation = new UserGeneration();
    private String accessToken;


    private static final String EXPECTED_MESSAGE_MUST_ADD_INGREDIENTS = "Ingredient ids must be provided";

    @After
    public void deleteUser() {
        if (accessToken != null) {
            stepUser.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Создание заказа, без ингингредиентов")
    @Description("Создание заказа, без ингингредиентов, проверяется статус код и ответ")
    public void createOrderWithOutIngredients() {
        step.createOrderWithOutIngredients()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_MUST_ADD_INGREDIENTS));
    }

    @Test
    @DisplayName("Создание заказа - позитивный тест без авторизации")
    @Description("Создание заказа, с ингредиентами проверяется статус код и ответ")
    public void createOrderWithOutAuth() {
        Order order = new Order();

        step.createOrder("", order.createOrderGeneration())
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа - позитивный тест с авторизацией")
    @Description("Создание заказа, с ингредиентами и авторизацией проверяется статус код и ответ")
    public void createOrderWithAuth() {

        UserCreate user = generation.newUser();
        UserCreateResponse userResponse = stepUser.createUser(user)
                .extract().as(UserCreateResponse.class);
        accessToken = userResponse.getAccessToken();
        Order order = new Order();

        step.createOrder(accessToken, order.createOrderGeneration())
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue());
    }


    @Test
    @DisplayName("Создание заказа - некорреткные данные")
    @Description("Создание заказа, с ингредиентами, которых нет в системе, проверяется статус код")
    public void createOrderWithIncorrectIngredients() {
        Order order = new Order();

        step.createOrder("", order.createIncorrectOrderGeneration())
                .log().all()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

}

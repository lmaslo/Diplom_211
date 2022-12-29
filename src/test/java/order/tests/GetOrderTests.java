package order.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import order.steps.OrderSteps;
import org.junit.Test;
import user.models.UserCreate;
import user.models.UserCreateResponse;
import user.models.UserGeneration;
import user.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderTests {
    OrderSteps step = new OrderSteps();

    UserSteps stepUser = new UserSteps();
    UserGeneration generation = new UserGeneration();
    private String accessToken;

    @Test
    @DisplayName("Получение заказов - позитивный тест, без авторизации")
    @Description("Получение заказов, проверяется статус код и ответ, без авторизации")
    public void getAllOrdersWithOutAuth() {
        step.getAllOrdersWithOutAuth()
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов - позитивный тест, для авторизованного пользователя")
    @Description("Получение заказов, проверяется статус код и ответ, для авторизованного пользователя")
    public void getAllOrdersWithAuth() {

        UserCreate user = generation.newUser();
        UserCreateResponse userResponse = stepUser.createUser(user)
                .extract().as(UserCreateResponse.class);
        accessToken = userResponse.getAccessToken();

        step.getOrdersWithAuth(accessToken)
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

}

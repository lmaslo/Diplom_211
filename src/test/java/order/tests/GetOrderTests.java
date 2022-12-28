package order.tests;
/*Получение заказов конкретного пользователя:
авторизованный пользователь,
неавторизованный пользователь.*/

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import order.steps.OrderSteps;
import org.junit.Test;

public class GetOrderTests {
    OrderSteps steps = new OrderSteps();

    @Test
    @DisplayName("Получение заказов - позитивный тест")
    @Description("Получение заказов, проверяется статус код и ответ")
    public void getAllOrders() {

        

}




}

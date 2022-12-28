package user.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import user.models.*;
import user.steps.UserSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class EditUserTests {

    UserSteps step = new UserSteps();
    UserGeneration generation = new UserGeneration();
    private String accessToken;

    private static final String EXPECTED_MESSAGE_SHOULD_BE_AUTH = "You should be authorised";

    @After
    public void deleteUser() {
        if (accessToken != null) {
            step.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Изменение пользователя")
    @Description("Изменение пользователя, параметра name и email, проверяется статус код и ответ")
    public void editUserWithAccessToken() {
        UserCreate userCreate = generation.newUser();
        UserCreateResponse userResponse = step.createUser(userCreate).extract().as(UserCreateResponse.class);
        accessToken = userResponse.getAccessToken();

        UserData userUpdate = new UserData(userCreate.getEmail() + "1", userCreate.getName() + "1");

        step.editUser(accessToken, userUpdate)
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo((userCreate.getEmail() + "1").toLowerCase()))
                .body("user.name", equalTo(userCreate.getName() + "1"));

    }

    @Test
    @DisplayName("Изменение пользователя без авторизации")
    @Description("Изменение пользователя без авторизации, параметра name и email, проверяется статус код и ответ")
    public void editUserWithOutAccessToken() {
        UserCreate userCreate = generation.newUser();
        UserCreateResponse userResponse = step.createUser(userCreate).extract().as(UserCreateResponse.class);
        accessToken = userResponse.getAccessToken();

        UserData userUpdate = new UserData(userCreate.getEmail() + "1", userCreate.getName() + "1");

        step.editUser("", userUpdate)
                .log().all()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_SHOULD_BE_AUTH));

    }





    /*Изменение данных пользователя:
    с авторизацией,
    без авторизации,
    Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.*/

}

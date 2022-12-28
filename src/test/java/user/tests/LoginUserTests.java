package user.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import user.models.User;
import user.models.UserCreate;
import user.models.UserCreateResponse;
import user.models.UserGeneration;
import user.steps.UserSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserTests {
    UserSteps step = new UserSteps();
    UserGeneration generation = new UserGeneration();
    private String accessToken;

    private static final String EXPECTED_MESSAGE_INCORRECT_AUTH = "email or password are incorrect";

    @After
    public void deleteUser() {
        if (accessToken != null) {
            step.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Авторизация пользователя - позитивный тест")
    @Description("Авторизация пользователя")
    public void loginUserTests() {
        UserCreate userCreate = generation.newUser();
        User user = new User(userCreate.getEmail(), userCreate.getPassword());

        step.createUser(userCreate);

        UserCreateResponse userResponse = step.authUser(user)
                .log().all()
                .statusCode(SC_OK)
                .extract().as(UserCreateResponse.class);

        Assert.assertNotNull(userResponse.getAccessToken());
        Assert.assertNotNull(userResponse.getRefreshToken());

        Assert.assertEquals(true, userResponse.getSuccess());
        Assert.assertEquals((user.getEmail().toLowerCase()), userResponse.getUser().getEmail());
        Assert.assertEquals(userCreate.getName(), userResponse.getUser().getName());

        accessToken = userResponse.getAccessToken();

    }

    @Test
    @DisplayName("Авторизация пользователя - негативный тест")
    @Description("Авторизация пользователя с рандомными login и password")
    public void loginUserRandomParamsTests() {
        User user = generation.newAuthUser();

        step.authUser(user)
                .log().all()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_INCORRECT_AUTH));
    }

    @Test
    @DisplayName("Авторизация пользователя - некорректный пароль")
    @Description("Авторизация пользователя с некорректным паролем")
    public void loginUserIncorrectPasswordTests() {
        UserCreate userCreate = generation.newUser();
        User user = new User(userCreate.getEmail(), userCreate.getIncorrectPassword());

        UserCreateResponse userResponse = step.createUser(userCreate).extract().as(UserCreateResponse.class);
        accessToken = userResponse.getAccessToken();


        step.authUser(user)
                .log().all()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_INCORRECT_AUTH));
    }



}

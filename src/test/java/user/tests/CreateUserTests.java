package user.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import user.models.UserCreate;
import user.models.UserCreateResponse;
import user.models.UserGeneration;
import user.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTests {
    UserSteps step = new UserSteps();
    UserGeneration generation = new UserGeneration();
    private String accessToken;


    private static final String EXPECTED_MESSAGE_DUPLICATE = "User already exists";
    private static final String EXPECTED_MESSAGE_WITHOUT_PARAMS = "Email, password and name are required fields";

    @After
    public void deleteUser() {
        if (accessToken != null) {
            step.deleteUser(accessToken);
        }
    }


    @Test
    @DisplayName("Создание пользователя - позитивный тест")
    @Description("Создание пользователя с рандомными параметрами")
    public void createUserTests() {
        UserCreate user = generation.newUser();

        UserCreateResponse userResponse = step.createUser(user)
                .log().all()
                .statusCode(SC_OK)
                .extract().as(UserCreateResponse.class);

        Assert.assertNotNull(userResponse.getAccessToken());
        Assert.assertNotNull(userResponse.getRefreshToken());
        Assert.assertEquals(true, userResponse.getSuccess());
        Assert.assertEquals((user.getEmail().toLowerCase()), userResponse.getUser().getEmail());
        Assert.assertEquals(user.getName(), userResponse.getUser().getName());

        accessToken = userResponse.getAccessToken();
    }

    @Test
    @DisplayName("Создание пользователя дубликат")
    @Description("Создание пользователя, с email, который уже есть в системе")
    public void createDuplicateUserTests() {
        UserCreate user = generation.newUser();

        step.createUser(user);

        step.createUser(user)
                .log().all()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_DUPLICATE));


    }

    @Test
    @DisplayName("Создание пользователя без Login")
    @Description("Создание пользователя без обязательного поля Login")
    public void createUserWithoutLoginTests() {
        UserCreate user = generation.newUser();
        user.setEmail(null);

        step.createUser(user)
                .log().all()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_WITHOUT_PARAMS));
    }

    @Test
    @DisplayName("Создание пользователя без password")
    @Description("Создание пользователя без обязательного поля password")
    public void createUserWithoutPasswordTests() {
        UserCreate user = generation.newUser();
        user.setPassword(null);

        step.createUser(user)
                .log().all()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_WITHOUT_PARAMS));

    }


    @Test
    @DisplayName("Создание пользователя без name")
    @Description("Создание пользователя без обязательного поля name")
    public void createUserWithoutNameTests() {
        UserCreate user = generation.newUser();
        user.setName(null);

        step.createUser(user)
                .log().all()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo(EXPECTED_MESSAGE_WITHOUT_PARAMS));
    }

}

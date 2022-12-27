package user.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import user.models.User;
import user.models.UserGeneration;
import user.steps.UserSteps;

public class CreateUserTests {
    UserSteps step = new UserSteps();
    UserGeneration generation = new UserGeneration();



    @Test
    @DisplayName("Создание пользователя - позитивный тест")
    @Description("1")
    //посмотреть про удаление пользователя после теста
    public void createUserTests() {
        User user = generation.newUser();

        step.createUser(user)
                .log().all()
                .statusCode(200);

        // добавить проверку боди
    }



    @Test
    @DisplayName("Создание пользователя дубликат")
    @Description("1")
    //посмотреть про удаление пользователя после теста
    public void createDuplicateUserTests() {
        User user = generation.newUser();

        step.createUser(user);

        step.createUser(user)
                .log().all()
                .statusCode(403);

        // добавить проверку боди
    }


    @Test
    @DisplayName("without LOGIN")
    @Description("1")
    //посмотреть про удаление пользователя после теста
    public void createUserWithoutLoginTests() {
        User user = generation.newUser();
        user.setEmail(null);

        step.createUser(user)
                .log().all()
                .statusCode(403);

        // добавить проверку боди
    }

    @Test
    @DisplayName("without password")
    @Description("1")
    //посмотреть про удаление пользователя после теста
    public void createUserWithoutPasswordTests() {
        User user = generation.newUser();
        user.setPassword(null);

        step.createUser(user)
                .log().all()
                .statusCode(403);

        // добавить проверку боди
    }


    @Test
    @DisplayName("without name")
    @Description("1")
    //посмотреть про удаление пользователя после теста
    public void createUserWithoutNameTests() {
        User user = generation.newUser();
        user.setName(null);

        step.createUser(user)
                .log().all()
                .statusCode(403);

        // добавить проверку боди
    }


}

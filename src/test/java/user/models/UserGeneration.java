package user.models;

import static utils.RandomString.*;


public class UserGeneration {
    private String email = getRandomEmail();
    //private String email = "Lena125389@yandex.ru";
    private String password = getRandomString(10);
    private String name = getRandomString(10);

    public UserCreate newUser() {
        return new UserCreate(email, password, name);
    }

    public User newAuthUser() {
        return new User(email, password);
    }
}

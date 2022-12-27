package user.models;

import static utils.RandomString.*;


public class UserGeneration {
    private String email = getRandomEmail();
    //private String email = "Lena12538@yandex.ru";
    private String password = getRandomString(10);
    private String name = getRandomString(10);

    public User newUser() {
        return new User(email, password, name);
    }
}

package user.models;
import static utils.RandomString.getRandomString;

public class UserGeneration {
    private String email = getRandomString(10)+"@yandex.ru";
    private String password= getRandomString(10);
    private String name= getRandomString(10);

    public User newUser(){
        return new User(email, password, name);
    }


}

package specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specs {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public static RequestSpecification request = with()
            .contentType(ContentType.JSON)
            .baseUri(BASE_URI);

}


package Operations.Handlers;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Request {
    private static final String BASE_URI = "http://localhost:8081";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    public void executeGetRequest(String path) {
                given()
                        .auth()
                        .basic(USERNAME, PASSWORD)
                        .baseUri(BASE_URI)
                        .contentType(ContentType.TEXT)
                        .when().get(path)
                        .then().log().body()
                        .extract().body().asPrettyString();
    }
}

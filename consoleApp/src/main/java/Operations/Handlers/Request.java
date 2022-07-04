package Operations.Handlers;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class Request {
    private static final String BASE_URI = "http://localhost:8081";
    public void executeGetRequest(String path){
        System.out.println(
                given()
                .baseUri(BASE_URI)
                .contentType(ContentType.TEXT)
                .when().get(path)
                .then().log().all()
                .extract().body().asPrettyString()
        );
    }
}

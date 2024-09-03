import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Base_Token {
    public String getToken() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String payload = "{\"username\": \"admin\",\"password\" : \"password123\"}";

        System.out.println(payload);

        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/auth");

        response.then().log().body();
        response.then().assertThat().statusCode(200);

        String token = response.body().asString();
        return token;
    }
}

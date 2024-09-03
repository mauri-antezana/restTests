import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Get {

    @Test
    public void getBookingIds(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .when().get("/booking");

        response.then().assertThat().statusCode(200);

        response.then().log().body();
    }

    @Test
    public void getBookingById() {
        String id = "1";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().get("/booking/{id}");

        response.then().assertThat().statusCode(200);

        response.then().log().body();
    }
}

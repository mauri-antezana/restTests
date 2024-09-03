import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Delete extends Base_Token {
    @Test
    public void updateBooking() throws JsonProcessingException {
        String token = getToken().substring(10,25);
        String id = "384";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Response response = RestAssured
                .given().contentType(ContentType.JSON)
                .cookie("token",token)
                .pathParam("id", id)
                .when().delete("/booking/{id}");

        response.then().assertThat().statusCode(201);
        response.then().log().body();
    }
}

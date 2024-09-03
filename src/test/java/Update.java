import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Update extends Base_Token {
    @Test
    public void updateBooking() throws JsonProcessingException {
        String token = getToken().substring(10,25);
        String id = "2764";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Booking booking = new Booking();
        BookingDates bookingDates = new BookingDates();

        booking.setFirstname("Mauricio");
        booking.setLastname("Antezana");
        booking.setTotalprice(1000);
        booking.setDepositpaid(true);
        bookingDates.setCheckin("2021-06-02");
        bookingDates.setCheckout("2022-06-02");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured
                .given().contentType(ContentType.JSON)
                .cookie("token",token)
                .body(payload)
                .pathParam("id", id)
                .when().put("/booking/{id}");

        response.then().assertThat().statusCode(200);
        response.then().log().body();
    }
}

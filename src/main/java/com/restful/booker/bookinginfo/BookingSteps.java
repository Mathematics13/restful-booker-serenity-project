package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.AuthorizationPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import java.util.HashMap;

public class BookingSteps
{
    @Step("Creating token for Booking with username :{0}, password :{1}")
    public ValidatableResponse createToken(String username, String password)
    {
        AuthorizationPojo authorizationPojo = new AuthorizationPojo();
        authorizationPojo.setUsername("admin");
        authorizationPojo.setPassword("password123");

       return SerenityRest. given()
                .contentType(ContentType.JSON)
                .header("Connection" , "keep-alive")
                .body(authorizationPojo)
                .when()
                .post(EndPoints.CREATE_TOKEN)
                .then()
                .log().all();
    }
    @Step("Create booking with firstname : {0} ,lastname : {1} ,totalprice : {2} ,depositpaid : {3},additionalneeds : {4}")
    public ValidatableResponse createBooking(String token, String firstname,String lastname,int totalprice,boolean depositpaid,String additionalneeds)
    {

        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin" , "2024-03-04");
        bookingdates.put("checkout" , "2024-04-04");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        bookingPojo.setBookingdates(bookingdates);

       return SerenityRest.given().log().all()
                .header("Connection" , "keep-alive")
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then().log().all();
    }
    @Step("Getting the booking information with User's Single Booking ID : {0}")
    public ValidatableResponse getBookingByID(int id)
    {
      return   SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all();
    }
    @Step("Update the user booking id : {0} ,firstname : {1} ,lastname : {2} ,totalprice : {3} ,depositpaid : {4},additionalneeds : {5}")
    public ValidatableResponse updateBooking(int id,String token,String firstname,String lastname,int totalprice,boolean depositpaid,String additionalneeds) {

        HashMap<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin" , "2024-04-25");
        bookingdates.put("checkout" , "2024-09-20");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        bookingPojo.setBookingdates(bookingdates);

       return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .header("Connection","keep-alive")
                .header("cookie", "token=2256cb373376bf6")
                .pathParam("bookingId" ,id)                                                         //for updation give id
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then().log().all();
    }

    @Step("Delete the user's booking information with bookingId : {0}")
    public ValidatableResponse deleteBooking(int id) {
      return  SerenityRest. given().log().all()
                .header("Content-Type", "application/json")
                .header("cookie", "token=2256cb373376bf6")
                .pathParam("bookingId" ,id)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then().log().all();
    }
    @Step("Getting User's booking information with booking Id : {0}")
    public ValidatableResponse getBookingById(int id)
    {
        return  SerenityRest.given().log().all()
                .pathParam("bookingId", id)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)                                         //Not hardcoding so from constants
                .then().log().all();
    }

    @Step("Getting the Ping Check : {0}")
    public ValidatableResponse gettingPingCheck() {
       return SerenityRest. given().log().all()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .get(EndPoints.PING)
                .then().log().all();
    }
}

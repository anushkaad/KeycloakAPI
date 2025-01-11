package builders;

import POJO.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static helper.APIEndPoints.*;
import static helper.testHelper.BASE_URL;
import static net.serenitybdd.rest.SerenityRest.given;


@Slf4j
public class User {

    public Response createUserEndpoint(createUser createUserBody, String authToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(createUserBody)
                .log().all()
                .post(BASE_URL + CREATE_USER)
                .then()
                .log().all() // Logs the response
                .extract()
                .response();
    }

    public Response getUserEndpoint(String authToken, String userId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .get(BASE_URL + String.format(GET_USER, userId))
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateUserEndpoint(updateUser updateUserBody, String authToken, String userId) {

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(updateUserBody)
                .log().all()
                .put(BASE_URL + String.format(UPDATE_USER, userId))
                .then()
                .log().all()
                .extract()
                .response();
    }

    public List<User> listUserEndpoint(String authToken) {
        Response response = given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .get(BASE_URL + LIST_USER)
                .then()
                .log().all()
                .extract()
                .response();

        List<User> listOfUser = response.jsonPath().getList("users", User.class);
        return listOfUser;
    }

    public Response countUsers(String authToken) {
        return given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .get(BASE_URL + COUNT_USER)
                .then()
                .log().all()
                .extract()
                .response();


    }

    public Response deleteUserEndpoint(String authToken, String userId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .delete(BASE_URL + String.format(DELETE_USER, userId))
                .then()
                .log().all()
                .extract()
                .response();

    }


    public Response disableEndpoint(enabled enabledBody, String authToken, String userId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(enabledBody)
                .log().all()
                .put(BASE_URL + String.format(ENABLE_DISABLE_USER, userId))
                .then()
                .log().all()
                .extract()
                .response();

        return response;
    }

}

package stepDefination;

import POJO.*;

import builders.User;
import helper.testHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import static builders.Authorization.getAccessToken;

import static helper.ErrorMessages.USER_NAME_MANDATORY_ERROR;
import static helper.TestDataBuild.*;
import static org.junit.Assert.*;

public class userStepDefination extends testHelper {

    Response createUserResponse;
    Response updateUserResponse;
    List<User> listOfUser;
    Response countResponse;
    Response deleteResponse;
    String accessToken;
    User user = new User();
    Response getUserResponse;
    Response disabledResponse;
    Response getRandomUser;

    @Given("I have a valid admin access token")
    public void i_have_a_valid_admin_access_token() throws IOException {
        accessToken = getAccessToken().getAccess_token();
    }

    @When("I try to create a new user with valid details")
    public void i_try_to_create_a_new_user_with_valid_details() {
        testHelper helper = new testHelper();
        createUserResponse = helper.executeCreateUser(accessToken);
    }

    @Then("I should get a successful response")
    public void i_should_get_a_successful_response() {
        assertEquals(createUserResponse.getStatusCode(), 201);
    }

    @Then("the response header should contain the created user's ID")
    public void the_response_header_should_contain_the_created_user_s_id() {
        testHelper helper = new testHelper();
        String userId = helper.getUserId();
        Assertions.assertThat(userId).describedAs("Verifying UserId is Not Null").isNotNull();
    }

    @Given("a user exist")
    public void a_user_exist() {
        i_try_to_create_a_new_user_with_valid_details();
    }

    @When("I try to retrieve that user")
    public void i_try_to_retrieve_that_user() {
        testHelper helper = new testHelper();
        String userId = helper.getUserId();
        getUserResponse = user.getUserEndpoint(accessToken, userId);
    }

    @Then("I should get a successful response as status code 200")
    public void i_should_get_a_successful_response_as_status_code_200() {
        assertEquals(getUserResponse.getStatusCode(), 200);
        getUser user = getUserResponse.as(getUser.class);
        Assertions.assertThat(user.getId()).describedAs("Verifying UserId is Not Null").isNotNull();
        Assertions.assertThat(user.getEmail()).describedAs("Verifying UserId is Not Null").isNotNull();
        Assertions.assertThat(user.getUsername()).describedAs("Verifying UserId is Not Null").isNotNull();

    }

    @Given("a user exists")
    public void a_user_exists() {
        i_try_to_create_a_new_user_with_valid_details();
    }

    @When("I try to update that user's details with valid data")
    public void i_try_to_update_that_user_s_details_with_valid_data() {
        testHelper helper = new testHelper();
        String userId = helper.getUserId();
        updateUser updateUserBody = updateUserPayload();
        updateUserResponse = user.updateUserEndpoint(updateUserBody, accessToken, userId);
    }

    @Then("the user's details should be updated successfully")
    public void the_user_s_details_should_be_updated_successfully() {
        assertEquals(updateUserResponse.getStatusCode(), 204);
    }

    @When("I try to list all users")
    public void i_try_to_list_all_users() {


        listOfUser = user.listUserEndpoint(accessToken);
    }

    @Then("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        Assert.assertNotNull(listOfUser);
        Assert.assertFalse(listOfUser.isEmpty());

    }

    @When("I try to count the number of users")
    public void i_try_to_count_the_number_of_users() {

        countResponse = user.countUsers(accessToken);
    }

    @Then("the response should return the correct count of users")
    public void the_response_should_return_the_correct_count_of_users() {
        assertEquals(countResponse.getStatusCode(), 200);
        List<User> listOfUser = countResponse.jsonPath().getList("users", User.class);
        Assertions.assertThat(listOfUser).describedAs("Verifying cout is Not Null").hasSizeGreaterThanOrEqualTo(0);

    }

    @When("I try to delete that user")
    public void i_try_to_delete_that_user() {
        i_try_to_create_a_new_user_with_valid_details();
        testHelper helper = new testHelper();
        String userId = helper.getUserId();
        deleteResponse = user.deleteUserEndpoint(accessToken, userId);
    }

    @Then("the user should no longer exist")
    public void the_user_should_no_longer_exist() {
        assertTrue("Verify the response body is empty", deleteResponse.getBody().asString().isEmpty());

    }

    @When("I try to disable that user")
    public void i_try_to_disable_that_user() {
        i_try_to_create_a_new_user_with_valid_details();
        testHelper helper = new testHelper();
        String userId = helper.getUserId();
        enabled enabledBody = enabledOrDisablePayload(false);
        disabledResponse = user.disableEndpoint(enabledBody, accessToken, userId);
    }

    @Then("the user should be disabled")
    public void the_user_should_be_disabled() {
        // Get the user details after disabling
        assertTrue("Verify the response body is empty", disabledResponse.getBody().asString().isEmpty());
    }


    //incomplete data
    @When("I try to create a new user with incomplete data")
    public void i_try_to_create_a_new_user_with_incomplete_data() {
        createUser createUserBody = createUserPayload(true);
        createUserBody.setUsername("");
        createUserResponse = user.createUserEndpoint(createUserBody, accessToken);
    }

    @Then("the 400 response should contain an error message Missing required fields")
    public void the_400_response_should_contain_an_error_message_missing_required_fields() {
        assertEquals(createUserResponse.getStatusCode(), 400);
        String errorMessage = createUserResponse.as(ErrorMessage.class).getErrorMessage();
        Assertions.assertThat(errorMessage).describedAs("Verifying Error Message is Correct").isEqualTo(USER_NAME_MANDATORY_ERROR);
    }


    @When("I try to retrieve a user by a non-existing ID")
    public void i_try_to_retrieve_a_user_by_a_non_existing_id() {
        String randomUserID = testHelper.getRandomUserId();

        getUserResponse = user.getUserEndpoint(accessToken, randomUserID);
    }

    @Then("I should get a {int} Not Found response")
    public void i_should_get_a_not_found_response(Integer int1) {
        assertEquals(getUserResponse.getStatusCode(), 404);
    }


    @When("I try to update a non-existing user with ID")
    public void i_try_to_update_a_non_existing_user_with_id() {

        updateUser updateUserBody = updateUserPayload();
        updateUserResponse = user.updateUserEndpoint(updateUserBody, accessToken, getRandomUserId());
    }

    @Then("I should get a 404 Not Found response with error message")
    public void I_should_get_a_404_Not_Found_response_with_error_message() {
        assertEquals(updateUserResponse.getStatusCode(), 404);

    }

    @When("I try to delete a non-existing user")
    public void i_try_to_delete_a_non_existing_user() {
        // String NonuserId = getRandomUserId();
        deleteResponse = user.deleteUserEndpoint(accessToken, getRandomUserId());
    }

    @Then("I should get a {int} Not Found response in delete")
    public void i_should_get_a_not_found_response_in_delete(Integer int1) {
        assertEquals(deleteResponse.getStatusCode(), 404);
    }

}

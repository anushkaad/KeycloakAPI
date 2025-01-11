package runner;


import builders.User;
import io.cucumber.java.After;
import io.restassured.response.Response;

import static builders.Authorization.getAccessToken;
import static helper.testHelper.retrieveUserIdFromSession;
import static org.junit.Assert.assertEquals;

public class Hooks {
    @After(value = "@PostDelete")
    public void deleteUserAfterScenario() {
        String accessToken = getAccessToken().getAccess_token();
        User user = new User();
        Response deleteResponse = user.deleteUserEndpoint(accessToken, retrieveUserIdFromSession());
        assertEquals(204, deleteResponse.getStatusCode());
    }
}

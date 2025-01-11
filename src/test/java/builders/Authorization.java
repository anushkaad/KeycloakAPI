package builders;


import POJO.getToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;

import static helper.APIEndPoints.GET_TOKEN;
import static helper.testHelper.*;
import static net.serenitybdd.rest.SerenityRest.given;

@Slf4j
public class Authorization {

    public static getToken getAccessToken() {
        return given()
                .contentType(String.valueOf(ContentType.APPLICATION_FORM_URLENCODED))
                .formParams("client_id", CLIENT_ID)
                .formParams("client_secret", CLIENT_SECRET)
                .formParams("grant_type", "client_credentials")
                .when().log().all()
                .post(BASE_URL + GET_TOKEN).as(getToken.class);
    }
}

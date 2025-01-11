package helper;

import POJO.createUser;
import builders.User;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.UUID;


import static helper.TestDataBuild.createUserPayload;

public class testHelper {
    public static RequestSpecification req;
    public static String BASE_URL;
    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String APPLICATION_JSON;
    public static String APPLICATION_FORM;
    private static String userId;
    Faker faker = new Faker();

    static {
        try {
            BASE_URL = getGlobalValue("baseUrl");
            CLIENT_ID = getGlobalValue("client_id");
            CLIENT_SECRET = getGlobalValue("client_secret");
            APPLICATION_JSON = getGlobalValue("application_json");
            APPLICATION_FORM = getGlobalValue("xform");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //To get global value
    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static RequestSpecification requestSpecification() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("client_id", getGlobalValue("client_id")) // Add client_id
                    .addQueryParam("client_secret", getGlobalValue("client_secret"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    //To generate random userId's
    public static String getRandomUserId() {
        return UUID.randomUUID().toString();
    }

    public static void storeUserIdInSession(String userId) {
        Serenity.setSessionVariable("userId").to(userId);
    }

    public static String retrieveUserIdFromSession() {
        return Serenity.sessionVariableCalled("userId");
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public String getRandomFistName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public Response executeCreateUser(String accessToken) {
        User user = new User();
        createUser createUserBody = createUserPayload(true);
        Response createUserResponse = user.createUserEndpoint(createUserBody, accessToken);

        if (createUserResponse.getStatusCode() == 201) {
            // Extract the LocationHeader
            String locationHeader = createUserResponse.getHeader("Location");
            System.out.println("Location Header: " + locationHeader); // Debugging: Check header

            // Extract userId from the location if present
            if (locationHeader != null) {
                userId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
                System.out.println("Extracted UserId: " + userId);
            } else {
                System.out.println("Location header is missing in the response");
            }
        } else {
            System.out.println("Failed to create user, Status Code: " + createUserResponse.getStatusCode());
        }
        return createUserResponse;

    }

    public String getUserId() {
        return userId;
    }
}

package tests.delete;

import data.User;
import data.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUserApiTest {

    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;

    static String emailId;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request();
        requestContext = request.newContext();
    }

    @AfterTest
    public void tearDown() {
        playwright.close();
    }


    public static String getRandomEmail() {
        emailId = "testautomation" + System.currentTimeMillis() + "@gmail.com";
        return emailId;
    }


    @Test
    public void deleteUserTest() throws IOException {

        Users users = Users.builder()
                .name("Kloia Automation")
                .email(getRandomEmail())
                .gender("male")
                .status("active").build();

        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                        .setData(users));

        System.out.println(apiPostResponse.url());
        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);

        String responseText = apiPostResponse.text();
        System.out.println(responseText);

        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText, User.class);
        System.out.println("actual user from the response---->");
        System.out.println(actUser);

        Assert.assertNotNull(actUser.getId());

        String userId = actUser.getId();
        System.out.println("new user id is : " + userId);


        APIResponse apiDELETEResponse = requestContext.delete("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
        );

        System.out.println(apiDELETEResponse.status());
        System.out.println(apiDELETEResponse.statusText());

        Assert.assertEquals(apiDELETEResponse.status(), 204);

        System.out.println("delete user response body ====" + apiDELETEResponse.text());

        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
        );

        System.out.println(apiResponse.text());

        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 404);
        Assert.assertEquals(apiResponse.statusText(), "Not Found");

        Assert.assertTrue(apiResponse.text().contains("Resource not found"));

    }


}

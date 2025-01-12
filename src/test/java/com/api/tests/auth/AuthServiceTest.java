package com.api.tests.auth;

import com.api.base.AuthService;
import com.api.base.UsersService;
import com.api.models.request.LoginRequest;
import com.api.models.request.SignupRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AuthServiceTest {

    protected static final Faker faker = new Faker();
    protected static final AuthService authService=new AuthService();;

    private String username;
    private String password;
    SignupRequest signupRequest;
    LoginResponse loginResponse;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        username=faker.name().username();
        password=faker.internet().password();
    }


    @Test(description = "Verify that user is able to create the account", groups = {"auth"})
    public void SignupTest() {
        signupRequest = new SignupRequest.Builder()
                .setUserName(username)
                .setPassword(password)
                .setEmail(faker.internet().emailAddress())
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setMobileNumber(faker.phoneNumber().subscriberNumber(10))
                .build();

        Response response = authService.signUp(signupRequest);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");

    }

    @Test(description = "Verify user is able to login using created users credentials", groups = {"auth"}, dependsOnMethods = {"SignupTest"})
    public void loginTest() {

        LoginRequest loginRequest = new LoginRequest(username, password);

        Response response = authService.login(loginRequest);

        Assert.assertEquals(response.statusCode(), 200);

        loginResponse = response.as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.token());
    }

    @Test(description = "Validated that created user data match with user profile data", groups={"users"}, dependsOnMethods = {"loginTest"})
    public void getUser(){
        String token = loginResponse.token();

        UsersService usersService = new UsersService();
        Response response = usersService.getProfile(token);

        Assert.assertEquals(response.statusCode(), 200);

        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);

        Assert.assertEquals(signupRequest.email(), userProfileResponse.email());
        Assert.assertEquals(signupRequest.firstName(), userProfileResponse.firstName());
        Assert.assertEquals(signupRequest.lastName(), userProfileResponse.lastName());
        Assert.assertEquals(signupRequest.username(), userProfileResponse.username());
        Assert.assertEquals(signupRequest.mobileNumber(), userProfileResponse.mobileNumber());

    }

}

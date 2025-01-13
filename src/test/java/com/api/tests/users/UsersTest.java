package com.api.tests.users;

import com.api.base.UsersService;
import com.api.models.request.CreateUsers;
import com.api.models.response.CreateUsersResponse;
import com.api.models.response.SingleUserResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UsersTest {

    protected static final Faker faker = new Faker();

    private String name;
    private String job;
    private String id;
    private UsersService usersService;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        name=faker.name().fullName();
        job=faker.job().position();
        usersService = new UsersService();
    }


    @Test(description = "Verify that user is able to create a user", groups = {"users"})
    public void createUserTest() {

        CreateUsers payload = new CreateUsers(name, job);

        Response response = usersService.CreateUser(payload);
        CreateUsersResponse createUsersResponse = response.as(CreateUsersResponse.class);

        id = createUsersResponse.id();

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(createUsersResponse.name(), payload.name());
        Assert.assertEquals(createUsersResponse.job(), payload.job());

    }

    @Test(description = "Verify created user data match with get api response", groups = {"users"}, dependsOnMethods = {"createUserTest"})
    public void getUserTest() {

        Response response = usersService.getUser("2");
        Assert.assertEquals(response.statusCode(), 200);

        SingleUserResponse singleUserResponse = response.as(SingleUserResponse.class);
        System.out.println(singleUserResponse);
    }

}

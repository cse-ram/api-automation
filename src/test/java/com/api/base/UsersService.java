package com.api.base;

import io.restassured.response.Response;

public class UsersService extends BaseService{
    private static final String BASE_PATH="/api/users";

    public Response getProfile(String token){
        setAuthToken(token);
        return getRequest(String.format("%s/profile", BASE_PATH));
    }
}

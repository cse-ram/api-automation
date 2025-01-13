package com.api.base;

import com.api.models.request.CreateUsers;
import io.restassured.response.Response;

public class UsersService extends BaseService{
    private static final String BASE_PATH="/api/users";

    public Response CreateUser(CreateUsers payload){
        return postRequest(payload, BASE_PATH);
    }

    public Response getUser(String id){
        return getRequest(String.format("%s/%s", BASE_PATH, id));
    }
}

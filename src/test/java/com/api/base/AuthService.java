package com.api.base;

import com.api.models.request.LoginRequest;
import com.api.models.request.SignupRequest;
import io.restassured.response.Response;

public class AuthService extends BaseService {
    private static final String BASE_PATH = "/api/auth";

    public Response login(LoginRequest payload) {
        return postRequest(payload, String.format("%s/login", BASE_PATH));
    }

    public Response signUp(SignupRequest payload){
        return postRequest(payload, String.format("%s/signup", BASE_PATH));
    }
}

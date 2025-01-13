package com.api.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.api.filters.LoggingFilter;
import utilities.ConfigReader;

public class BaseService {

    private static final String BASE_URL;
    private ThreadLocal<RequestSpecification> requestSpecification = ThreadLocal.withInitial(() -> RestAssured.given().baseUri(BASE_URL));

    static {
        ConfigReader.load("qa");
        BASE_URL = ConfigReader.get("base.uri");
        RestAssured.filters(new LoggingFilter());
    }

    protected Response getRequest(String endPoint) {
        return requestSpecification.get().get(endPoint);
    }

    protected Response postRequest(Object payload, String endPoint) {
        return requestSpecification.get().contentType(ContentType.JSON).body(payload).post(endPoint);
    }

    protected Response putRequest(Object payload, String endPoint) {
        return requestSpecification.get().contentType(ContentType.JSON).body(payload).put(endPoint);
    }
}

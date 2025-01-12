package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoggingFilter implements Filter {
    private static final Logger logger  = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec);
        Response response = ctx.next(requestSpec, responseSpec);
        logResponse(response);
        return response;
    }

    public void logRequest(FilterableRequestSpecification requestSpec){
        logger.info("BASE URI: {}", requestSpec.getBaseUri());
        logger.info("Headers: {}", requestSpec.getHeaders());
        logger.info("Payload: {}", Optional.ofNullable(requestSpec.getBody()));
    }

    public void logResponse(Response response){
        logger.info("STATUS CODE: {}", response.statusCode());
        logger.info("Response Headers: {}", response.getHeaders());
        logger.info("Response Body: {}", response.getBody().prettyPrint());
    }
}

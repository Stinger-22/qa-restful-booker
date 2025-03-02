package com.restfulbooker.api.util;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static org.testng.Assert.assertTrue;

public class ResponseTimeFilter implements Filter {
    private final long maxTime;

    public ResponseTimeFilter(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        final Response response = ctx.next(requestSpec, responseSpec);
        long time = response.getTime();
        assertTrue(time < maxTime, "Response time should be less than " + maxTime + " ms");
        return response;
    }
}

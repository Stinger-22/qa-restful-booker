package com.restfulbooker.api.tests;

import com.restfulbooker.api.BaseAPI;
import com.restfulbooker.api.requests.PingAPI;
import com.restfulbooker.api.specification.response.ResponsePing;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

public class TestPing extends BaseAPI {
    @Test
    void testPingIsWorking() {
        ResponseSpecification expectedResponse = ResponsePing.expectedPing();

        Response ping = PingAPI.ping();

        ping.then()
            .spec(expectedResponse);
    }
}

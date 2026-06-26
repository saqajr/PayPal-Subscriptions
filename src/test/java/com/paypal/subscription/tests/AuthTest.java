package com.paypal.subscription.tests;

import com.paypal.subscription.auth.AuthClient;
import com.paypal.subscription.config.Env;
import com.paypal.subscription.utils.LogsUtils;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class AuthTest {


    @Test
    public void assertSuccessfulFetchingToken(){
        Response response = new AuthClient().fetchAccessToken();
        assertEquals(response.getStatusCode(),200);
        response.then()
                .body("access_token",not(emptyOrNullString()))
                .body("token_type",equalTo("Bearer"))
                .body("expires_in",greaterThan(0)) ;
        LogsUtils.info("Fetching Access Token Success");
    }
    @Test
    public void assertInvalidCredentials(){
        Response response = new AuthClient().fetchAccessToken("invalid-client-id",Env.get("CLIENT_SECRET"));
        assertEquals(response.getStatusCode(),401);
        response.then()
                .body("error", equalTo("invalid_client"))
                .body("error_description", equalTo("Client Authentication failed"));
    }

}

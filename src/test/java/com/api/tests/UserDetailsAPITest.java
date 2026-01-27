package com.api.tests;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constant.Role.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
	   .when()
			.get("userdetails")
	   .then()
	   		.spec((SpecUtil.responseSpec_OK()))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}

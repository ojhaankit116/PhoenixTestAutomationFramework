package com.api.tests;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static com.api.constant.Role.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {

	@Test(description="Verify if the UserDetailsAPI shows correct response", groups={"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		given()
			.spec(requestSpecWithAuth(FD))
	   .when()
			.get("userdetails")
	   .then()
	   		.spec((responseSpec_OK()))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}

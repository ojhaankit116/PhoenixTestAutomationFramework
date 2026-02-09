package com.api.tests.datadriven;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPIDataDrivenTest {
	
	@Test(description = "Verifying if login api is working for FD user", 
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProvidersUtils.class, 
			dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean) {
		
		given()
			.spec(requestSpec(userbean))
		.when()
			.post("login")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message",equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}

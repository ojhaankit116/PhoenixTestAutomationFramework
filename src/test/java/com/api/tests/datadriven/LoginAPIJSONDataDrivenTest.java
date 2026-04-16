package com.api.tests.datadriven;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPIJSONDataDrivenTest {
	
	@Test(description = "Verifying if login api is working for FD user", 
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProvidersUtils.class, 
			dataProvider = "LoginJobAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {
		
		given()
			.spec(requestSpec(userCredentials))
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

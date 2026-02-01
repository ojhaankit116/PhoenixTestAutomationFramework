package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test(description="Verify if the MasterAPI shows correct response", groups={"api","regression","smoke"})
	public void masterAPITest() {
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("master")//it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body("data", notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data", hasKey("map_fst_pincode"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", equalTo(2))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_role.size()", greaterThanOrEqualTo(0))
		.body("data.mst_role.id", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test(description="Verify if the MasterAPI shows correct response code for Invalid or Missing Token", groups={"api","negative","regression","smoke"})
	public void invalidTokenMasterAPITest() {
		given()
		.spec(requestSpec())
		.when()
		.post("master") //it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.spec(responseSpec_TEXT(401));
		
	}
}

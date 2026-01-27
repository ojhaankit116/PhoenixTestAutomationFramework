package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master")//it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.and()
		.body("data", Matchers.notNullValue())
		.body("data",Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("map_fst_pincode"))
		.body("$", Matchers.hasKey("message"))
		.body("$", Matchers.hasKey("data"))
		.body("data.mst_oem.size()", Matchers.equalTo(2))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		.body("data.mst_role.size()", Matchers.greaterThanOrEqualTo(0))
		.body("data.mst_role.id", Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("master") //it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
		
	}
}

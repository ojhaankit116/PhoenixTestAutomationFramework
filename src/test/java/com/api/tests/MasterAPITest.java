package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization", AuthTokenProvider.getToken(Role.FD))
		.and()
		.contentType("") //so we explicitly said it should be empty
		.log().all()
		.log().uri()
		.log().body()
		.log().headers()
		.log().method()
		.when()
		.post("master")//it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.log().all()
		.statusCode(200)
		.and()
		.time(Matchers.lessThan(1000L))
		.and()
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
	public void invalidTokenMAsterAPITest() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization", "")
		.and()
		.contentType("") //explicitly saying it should be empty
		.log().all()
		.when()
		.post("master") //it should be get request let developer know //whenever making post-default content-type application/url-formenceded is added by rest assured if not added
		.then()
		.log().all()
		.statusCode(401);
		
	}
}

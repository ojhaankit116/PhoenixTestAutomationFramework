package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;


public class CreateJobAPITestWithFakerData {
	
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description="Creating the CreateJobAPI Payload ")
	public void setup() {
		
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

	}
	
	@Test(description="Verify if the CreateJobAPI is able to create Inwarranty job",groups= {"api","smoke","regression"})
	public void createJobAPITest() {
				
		given()
		.spec(requestSpecWithAuth(Role.FD,createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_platform_id", equalTo(2))
		.body("data.job_number", startsWith("JOB_"));
	}
}

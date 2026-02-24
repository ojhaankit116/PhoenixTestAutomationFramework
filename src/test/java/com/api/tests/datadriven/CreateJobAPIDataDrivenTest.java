package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.request.model.CreateJobPayload;


public class CreateJobAPIDataDrivenTest {
	
	@Test(description="Verify if the CreateJobAPI is able to create Inwarranty job",groups= {"api","datadriven","regression","csv"},
			dataProviderClass = com.dataproviders.DataProvidersUtils.class,
			dataProvider = "CreateJobAPIDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload) {
				
		given()
		.spec(requestSpecWithAuth(FD,createJobPayload))
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

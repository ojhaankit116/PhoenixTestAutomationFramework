package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		//Creating the CreateJobPayload Object
		Customer customer = new Customer("Ankit", "Ojha", "8634214986", "", "jhsdhf@gmail.con", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "GTA", "BTA", "MTA", "Mumbai", "989898", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-05-01T04:00:00.000Z", "46594500302521", "46594500302521", "46594500302521", "2025-05-01T04:00:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_platform_id", Matchers.equalTo(2))
		.body("data.job_number", Matchers.startsWith("JOB_"));
	}
}

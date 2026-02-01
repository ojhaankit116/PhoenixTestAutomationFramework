package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.WarrantyStatus;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		
		//Creating the CreateJobPayload Object
		Customer customer = new Customer("Ankit", "Ojha", "8634214986", "", "jhsdhf@gmail.con", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "GTA", "BTA", "MTA", "Mumbai", "989898", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "46594500302525", "46594500302525", "46594500302525", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_CENTER_A.getCode(), Platform.FRONT_DESK.getCode(), WarrantyStatus.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		
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

package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;


public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		//Creating the CreateJobPayload Object
		Customer customer = new Customer("Ankit", "Ojha", "8634214986", "", "jhsdhf@gmail.con", "");
		CustomerAddress customerAddress = new CustomerAddress("21", "GTA", "BTA", "MTA", "Mumbai", "989898", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-05-01T04:00:00.000Z", "35594500302521", "35594500302521", "35594500302521", "2025-05-01T04:00:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
			
	}
}

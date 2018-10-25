package com.qa;

import org.json.simple.JSONObject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {
	RequestSpecification request = RestAssured.given();
	String id ="26";
	boolean replaceExisting;
	ExtentReports extent = new ExtentReports("C:\\Users\\Admin\\Desktop\\RESTAssured Test\\AssessmentReport.html", replaceExisting);
	ExtentTest test;
	
	@After
	public void teardown() {
		extent.endTest(test);
		extent.flush();
	}

	@Given("^a users list exists that returns status code (\\d+)$")
	public void a_users_list_exists_that_returns_status_code(int arg1) {
		test = extent.startTest("Searching List of owners.");
	    System.out.println("Given: -------------------------------------------");
	    Response ownersList = request.get("http://10.0.10.10:9966/petclinic/api/owners");
	    ownersList.then().statusCode(arg1);
	    ownersList.prettyPrint();
	    System.out.println("Given: -------------------------------------------");
	    test.log(LogStatus.PASS, "List of Owners Found.");
	}

	@SuppressWarnings("unchecked")
	@When("^a user is created and posted to the site$")
	public void a_user_is_created_and_posted_to_the_site(){
		test.log(LogStatus.INFO, "Creating a new Owner.");
		System.out.println("First When: --------------------------------------");
		request.header("Content-Type", "application/json");
		JSONObject ownerPost = new JSONObject();
		ownerPost.put("id", "26");
		ownerPost.put("firstName", "Jacob");
		ownerPost.put("lastName", "Dancer");
		ownerPost.put("address", "31 Madeup Street");
		ownerPost.put("city", "Manchester");
		ownerPost.put("telephone", "01215647235");
		ownerPost.put("pets", "");
		request.body(ownerPost);
		Response ownersList = request.post("http://10.0.10.10:9966/petclinic/api/owners");
		ownersList.prettyPrint();
		System.out.println("First When: --------------------------------------");
		test.log(LogStatus.PASS, "Created a new Owner.");
	}

	@SuppressWarnings("unchecked")
	@When("^the same user is then updated$")
	public void the_same_user_is_then_updated(){
		test.log(LogStatus.INFO, "Updating an Owner.");
		System.out.println("Second When: -------------------------------------");
		Response updateOwner = request.get("http://10.0.10.10:9966/petclinic/api/owners/" + id);
		request.header("Content-Type", "application/json");
		JSONObject ownerPut = new JSONObject();
		ownerPut.put("id", "26");
		ownerPut.put("firstName", "Steve");
		ownerPut.put("lastName", "Dancer");
		ownerPut.put("address", "31 Madeup Street");
		ownerPut.put("city", "Manchester");
		ownerPut.put("telephone", "01215647235");
		ownerPut.put("pets", "");
		request.body(ownerPut);
		Response ownersList = request.post("http://10.0.10.10:9966/petclinic/api/owners/");
		ownersList.prettyPrint();
		System.out.println("Second When: -------------------------------------");
		test.log(LogStatus.PASS, "Updated an Owner.");
	}

	@Then("^searching for that user returns status code (\\d+)$")
	public void searching_for_that_user_returns_status_code(int arg1) {
		test.log(LogStatus.INFO, "Searching for that Owner.");
		System.out.println("Then: --------------------------------------------");
		Response ownerOne = request.get("http://10.0.10.10:9966/petclinic/api/owners/" + id);
		ownerOne.then().statusCode(arg1);
		ownerOne.prettyPrint();
		System.out.println("Then: --------------------------------------------");
		test.log(LogStatus.PASS, "Found that Owner.");
	}
	
}

package com.qa;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	WebDriver driver;
	boolean replaceExisting;
	ExtentReports extent = new ExtentReports("C:\\Users\\Admin\\Desktop\\Advanced Automated Testing Assessment\\AssessmentReport.html", replaceExisting);
	ExtentTest test;
	Constants a = new Constants();
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(a.home);
	}
	
	@After
	public void teardown() {
		extent.endTest(test);
		driver.close();
		extent.flush();
	}
		
	@Given("^a users list exists$")
	public void a_users_list_exists(){
	    test = extent.startTest("Going to the Owners Screen.");
	    driver.get(a.owners);
	    assertEquals(a.owners, driver.getCurrentUrl());
	    test.log(LogStatus.PASS, "Able to get to Owners Screen.");
	}

	@When("^a user is created and posted to the site$")
	public void a_user_is_created_and_posted_to_the_site(){
	    test.log(LogStatus.INFO, "Creating an Owner");
	    driver.get(a.addOwner);
	    String name = "Katie";
	    String surname = "Burling";
	    String address = "29 Spring Avenue";
	    String city = "Birmingham";
	    String telephone = "01215332692";
	    AddOwnerPage b = PageFactory.initElements(driver, AddOwnerPage.class);
	    b.createOwner(name, surname, address, city, telephone);
	    b.submitOwner();
	    test.log(LogStatus.PASS, "Owner Created.");
	}

	@When("^the same user is then updated$")
	public void the_same_user_is_then_updated() {
		String id = "14";
		String newName = "Terry";
		test.log(LogStatus.INFO, "Updating an Owner");
	    driver.get(a.editStem + id + "/edit");
	    WebElement changeName = driver.findElement(By.id("firstName"));
	    changeName.clear();
	    changeName.sendKeys(newName);
	    WebElement btn = driver.findElement(By.xpath("/html/body/app-root/app-owner-edit/div/div/form/div[7]/div/button[2]"));
	    btn.click();
	    test.log(LogStatus.PASS, "Owner Name Updated.");
	}

	@Then("^searching for that user$")
	public void searching_for_that_user(){
		String newName = "Terry Burling";
		driver.get(a.owners);
		assertEquals(false, driver.findElements(By.linkText(newName)).isEmpty());
		test.log(LogStatus.PASS, "Updated Owner does Exist.");
	}
}

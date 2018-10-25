package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddOwnerPage {

	@FindBy(id = "firstName")
	private WebElement firstName;
	@FindBy(id = "lastName")
	private WebElement lastName;
	@FindBy(id = "address")
	private WebElement address;
	@FindBy(id = "city")
	private WebElement city;
	@FindBy(id = "telephone")
	private WebElement telephone;
	@FindBy(xpath = "/html/body/app-root/app-owner-add/div/div/form/div[7]/div/button[2]")
	private WebElement submit;
	
	public void createOwner(String firstname, String lastname, String address1, String city1, String telephone1) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		address.sendKeys(address1);
		city.sendKeys(city1);
		telephone.sendKeys(telephone1);
	}
	
	public void submitOwner() {
		submit.click();
	}
}

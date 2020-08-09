package com.makemytrip.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.makemytrip.base.MakeMyTripBase;

public class LoginPage extends MakeMyTripBase {

	public String path;

	public LoginPage() {
		super(System.getProperty("user.dir") + "//FlightDetails.xlsx");
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	/****************
	 * All @FindBy annotations are derived from PageFactory class
	 ***************/

	@FindBy(xpath = "//*[@id=\"SW\"]/div[1]/div[1]/ul/li[6]/div[1]")
	WebElement loginPhone;

	@FindBy(xpath = "//input[@class='font14 fullWidth']")
	WebElement phoneText;

	@FindBy(xpath = "//button[@class='capText font16']")
	WebElement continueButton;

	@FindBy(xpath = "//span[@data-cy='serverError']")
	WebElement invalidMessage;

	@FindBy(xpath = "//p[@class='modalTitle makeFlex hrtlCenter font26 latoBold appendBottom5']")
	WebElement otp;

	@FindBy(id = "webklipper-publisher-widget-container-notification-frame")
	WebElement popup;

	@FindBy(xpath = "/html/body/div/div[2]/div/div/a/i")
	WebElement close;

	// invalid login method
	public void invalidLogin(ExtentTest logger, String sheetname) throws InterruptedException {
//		ExplicitWait(popup);
//
//		driver.switchTo().frame(popup);
//
//		ExplicitWait(close);
//
//		close.click();
//
		driver.navigate().refresh();

		Thread.sleep(2000);

		click(loginPhone);
		
		logger.log(Status.INFO, "Click on [Login with Phone/Email]");

		sendTextFromExcel(phoneText, sheetname, 2, "Invalid Phone Number");

		logger.log(Status.INFO, "Enter invalid Phone Number");

		click(continueButton);

		logger.log(Status.INFO, "Click on [CONTINUE] button");
	}

	// assertion of invalid login message
	public String assertInvalidLogin() {
		
		ExplicitWait(invalidMessage);

		String errormsg = invalidMessage.getText();
		return errormsg;
	}

	// taking invalid login screenshot
	public void takeInvalidLoginScreenshot(ExtentTest logger) throws IOException, InterruptedException {

		Thread.sleep(2000);

		takeScreenshotWhenPassed("invalidLogin");

		logger.error("Wrong message displayed on log in",
				MediaEntityBuilder.createScreenCaptureFromPath(
						System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("invalidLogin") + "")
						.build());
	}

	// valid login test
	public void validLogin(ExtentTest logger, String sheetname) {

		phoneText.clear();

		sendTextFromExcel(phoneText, sheetname, 2, "Valid Phone Number");

		logger.log(Status.INFO, "Enter Valid Phone Number");

		click(continueButton);

		logger.log(Status.INFO, "Click on [CONTINUE] button");
	}

	// assertion of valid login message
	public String assertValidLogin() {
		ExplicitWait(otp);
		String msg = otp.getText();
		return msg;
	}

	// taking valid login screenshot
	public void takeValidLoginScreenshot(ExtentTest logger) throws IOException, InterruptedException {

		Thread.sleep(1000);

		takeScreenshotWhenPassed("validLogin");

		logger.pass("Right message displayed on log in",
				MediaEntityBuilder.createScreenCaptureFromPath(
						System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("validLogin") + "")
						.build());
	}
}

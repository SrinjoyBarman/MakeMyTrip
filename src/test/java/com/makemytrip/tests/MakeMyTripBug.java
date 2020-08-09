package com.makemytrip.tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.makemytrip.base.MakeMyTripBase;
import com.makemytrip.pages.FlightsPage;
import com.makemytrip.pages.HomePage;
import com.makemytrip.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class MakeMyTripBug extends MakeMyTripBase{
	
	public static String browserName;
	public HomePage homepage;
	public FlightsPage flightspage;
	public LoginPage loginpage;

	/********************
	 * All methods called in the form of homepage."method", flightspage."method" and
	 * loginpage."method" are being referred from the POM class
	 *********************/
	// constructor
	public MakeMyTripBug() {
		super(System.getProperty("user.dir") + "//FlightDetails.xlsx");
		// TODO Auto-generated constructor stub
	}

	// parameters accepted from respective xml file.
	// setting up driver and property file.
	@Parameters({ "browsername", "portId" })
	@BeforeClass(alwaysRun = true)
	public void setUp(String browsername, String portId) throws MalformedURLException {
		invokeBrowser(browsername, portId);

		browserName = browsername;

		homepage = new HomePage(System.getProperty("user.dir") + "//FlightDetails.xlsx");

		flightspage = new FlightsPage(System.getProperty("user.dir") + "//FlightDetails.xlsx");

		loginpage = new LoginPage();
	}
	
	@Test(priority = 0, groups = {"Init"})
	public void getWebsite() {
		getUrl("websiteUrl");
	}
	
	@Test(priority = 1, groups = { "Init" })
	@Description("Verify navigation to [Make My Trip], and enter invalid details")
	@Feature("Feature : Navigation & Incorrect form details")
	@Epic("Defect Id- D1")
	@Story("Story: Fields unavailibility")
	@Step("Verify whether incorrect details provide invalid outcome")
	@Severity(SeverityLevel.CRITICAL)
	public void homePageDetails() throws InterruptedException {
		if (browserName.equalsIgnoreCase("chrome"))
			logger = report.createTest("Init Test MMT - Chrome");
		else if (browserName.equalsIgnoreCase("mozilla"))
			logger = report.createTest("Init Test MMT - Mozilla");

		logger1 = logger.createNode("MMT Invalid Search");

		logger1.log(Status.INFO, "Initialize the browser");

		logger1.log(Status.INFO, "Navigate to URL");

		Assert.assertEquals(homepage.assertTitle().split("-")[0].trim(), "MakeMyTrip");

		logger1.log(Status.PASS, "Assert whether webpage title matches [MakeMyTrip]");

		String returnederr = homepage.enterInvalidDetails(logger1);

		Assert.assertEquals(returnederr, "Upto 9 passengers allowed");

		logger1.log(Status.FAIL, "You can't select more than 9 passengers");
		
		String err= homepage.takeScreenshot(logger1);
		
		Assert.assertEquals("No flights found", err);
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger1.log(Status.FAIL, "Failed Test is" + result.getName());
			logger1.log(Status.FAIL, result.getThrowable());
			logger1.fail("Failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotWhenFailed()).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger1.log(Status.SKIP, "Skipped Test is" + result.getName());
			logger1.log(Status.SKIP, result.getThrowable());
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void quit() throws Exception {
		report.flush();
		close();
	}
}

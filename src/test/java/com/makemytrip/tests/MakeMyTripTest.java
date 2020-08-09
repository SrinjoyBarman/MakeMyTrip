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

public class MakeMyTripTest extends MakeMyTripBase {
	public static String browserName;
	public HomePage homepage;
	public FlightsPage flightspage;
	public LoginPage loginpage;

	/********************
	 * All methods called in the form of homepage."method", flightspage."method" and
	 * loginpage."method" are being referred from the POM class
	 *********************/
	// constructor
	public MakeMyTripTest() {
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

	// test to get respective URL
	@Test(priority = 0, groups = { "Smoke", "Regression" })
	public void getWebsite() {
		getUrl("websiteUrl");
	}

	/********************
	 * Following function calls HomePage.java
	 ********************/
	// smoke test
	@Test(priority = 1, groups = { "Smoke" })
	@Description("Verify navigation to [Make My Trip], enter details and assert whether Friday is selected")
	@Feature("Feature : Navigation & Correct form details")
	@Epic("Flight_Search_01,02,03,04,05,06")
	@Story("Story: Fields Availibility")
	@Step("Verify whether correct details are entered in the form and correct date is selected")
	@Severity(SeverityLevel.CRITICAL)
	public void homePageDetails() throws InterruptedException {
		if (browserName.equalsIgnoreCase("chrome"))
			logger = report.createTest("Smoke Test MMT - Chrome");
		else if (browserName.equalsIgnoreCase("mozilla"))
			logger = report.createTest("Smoke Test MMT - Mozilla");

		logger1 = logger.createNode("MMT Home Page");

		logger1.log(Status.INFO, "Initialize the browser");

		logger1.log(Status.INFO, "Navigate to URL");

		Assert.assertEquals(homepage.assertTitle().split("-")[0].trim(), "MakeMyTrip");

		logger1.log(Status.PASS, "Assert whether webpage title matches [MakeMyTrip]");

		String returnedDay = homepage.enterDetails(logger1);

		Assert.assertEquals(returnedDay, "Friday");

		logger1.log(Status.PASS, "[Friday] is selected");
	}

	/*********************
	 * Following function calls FlightsPage.java
	 ************************/

	// FlightExcelSheet parameter accepted from xml file
	
	@Parameters("FlightsExcelSheet")
	@Test(priority = 2, groups = "Smoke")
	@Description("Verify navigation to [Flights] Page, sort price by [High to Low] order, write details of first three flights in Excel File")
	@Feature("Feature : Storing of correct Flight Details")
	@Epic("Flight_Search_07")
	@Story("Story: Flight Details")
	@Step("Verify whether correct details are written in excel sheet of flights sorted [High to Low]")
	@Severity(SeverityLevel.CRITICAL)
	public void getProducts(String sheetName) throws InterruptedException {

		logger1 = logger.createNode("MMT Flights Page");

		Assert.assertEquals(homepage.assertTitle().split("-")[0].trim(), flightspage.assertTitle());

		logger1.log(Status.PASS, "[Home Page] and [Flights Page] title doesn't match");

		String s[][] = flightspage.writeDetails();

		boolean b = setCellData(sheetName, s);

		Assert.assertTrue(b, "Flight Details are correctly written in respective Excel File");

		logger1.log(Status.PASS, "First five Flights are correctly written in Excel File");
	}

	/**********************
	 * Following function calls FlightsPage.java's screenshot
	 * 
	 * @throws InterruptedException
	 ********************/
	// takes screenshot
	@Test(priority = 3, groups = "Smoke")
	@Description("Verify whether flights are available and take screenshot")
	@Feature("Feature : Availibility of Flights")
	@Epic("Flight_Search_07")
	@Story("Story: Flight Availibility")
	@Step("Verify whether flights are available")
	@Severity(SeverityLevel.MINOR)
	public void screenshotRequired() throws InterruptedException {

		logger1.log(Status.INFO, "Take screenshots of all the five upcoming Flights on Friday");

		flightspage.takingScreenshot(logger1);

	}

	/**********************
	 * Following function calls LoginPage.java
	 **********************/
	// Regression Test- Invalid
	@Test(priority = 4, groups = "Regression")
	@Parameters("readExcel")
	@Description("Verify [login] functionality of the application by providing [Invalid phone] details.")
	@Feature("Feature : Invalid Login")
	@Epic("Mmt_01")
	@Story("Story: Invalid Login")
	@Step("Verify whether invalid [Phone number] details give negative results.")
	@Severity(SeverityLevel.BLOCKER)
	public void invalidLoginNow(String readExcel) throws InterruptedException, IOException {
		if (browserName.equalsIgnoreCase("chrome"))
			logger = report.createTest("Regression Test MMT - Chrome");
		else if (browserName.equalsIgnoreCase("mozilla"))
			logger = report.createTest("Regression Test MMT - Mozilla");

		logger1 = logger.createNode("Invalid Login Test");

		loginpage.invalidLogin(logger1, readExcel);

		logger1.warning("Test Case may fail if given message does not match the displayed one.");

		Assert.assertEquals("Invalid phone number", loginpage.assertInvalidLogin());

		logger1.log(Status.FAIL, "Should display [Enter OTP] but showing [Invalid phone number]");

		logger1.log(Status.INFO, "Take screenshot of invalid login");

		loginpage.takeInvalidLoginScreenshot(logger1);
	}

	/**********************
	 * Following function calls LoginPage.java
	 **********************/
	// Regression Test- Valid
	@Test(priority = 5, groups = "Regression")
	@Parameters("readExcel")
	@Description("Verify [login] functionality of the application by providing [Valid phone] details.")
	@Feature("Feature : Valid Login")
	@Epic("Mmt_01")
	@Story("Story: Valid Login")
	@Step("Verify whether valid [Phone number] details give positive results.")
	@Severity(SeverityLevel.BLOCKER)
	public void validLoginNow(String readExcel) throws IOException, InterruptedException {
		logger1 = logger.createNode("Valid Login Test");

		loginpage.validLogin(logger1, readExcel);
		Thread.sleep(2000);

		logger1.warning("Test Case may fail if given message does not match the displayed one.");
		Thread.sleep(2000);

		Assert.assertEquals("Enter OTP", loginpage.assertValidLogin());

		logger1.log(Status.PASS, "Should display [Enter OTP] and showing [Enter OTP]");

		logger1.log(Status.INFO, "Take screenshot of valid login");

		loginpage.takeValidLoginScreenshot(logger1);
	}

	// method to display result when scripts fail
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
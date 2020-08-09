package com.makemytrip.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.makemytrip.base.MakeMyTripBase;


public class FlightsPage extends MakeMyTripBase{
	
	public FlightsPage(String path) {
		super(path);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	/****************All @FindBy annotations are derived from PageFactory class***************/
	
	@FindBy(xpath="//span[@class='pointer']/span[contains(text(),'Price')]")
	WebElement sortByPrices;
	
	@FindBy(xpath="//span[@class='airways-name ']")
	List<WebElement> flightName;
	
	@FindBy(xpath="//p[@class='fli-code']")
	List<WebElement> flightNum;
	
	@FindBy(xpath="//p[@class='dept-city']")
	List<WebElement> depCity;
	
	@FindBy(xpath="//div[@class='dept-time']")
	List<WebElement> depTime;
	
	@FindBy(xpath="//p[@class='arrival-city']")
	List<WebElement> arrCity;
	
	@FindBy(xpath="//p[@class='reaching-time append_bottom3']")
	List<WebElement> arrTime;
	
	@FindBy(xpath="//p[@class='fli-duration']")
	List<WebElement> flightDur;
	
	@FindBy(xpath="//p[@class='fli-stops-desc']")
	List<WebElement> flightStopsDesc;
	
	@FindBy(xpath="//div[@class='fli-list-body-section']/div/div/div[3]/p/span")
	List<WebElement> flightsFare;
	
	@FindBy(xpath="//div[@class='pull-left make_relative']/button")
	List<WebElement> individualDetails;
	
	//assert the title of flights page
	public String assertTitle() throws InterruptedException {
	
		driver.navigate().refresh();
		return driver.getTitle();
		
	}
	
	//get details of flights in excel file and return excel data
	public String[][] writeDetails() throws InterruptedException {
		Thread.sleep(2000);
		
		ExplicitWait(sortByPrices);
		click(sortByPrices);
		
		String excelData[][]= getPrices(flightName, flightNum, depCity, depTime, arrCity,
				arrTime, flightDur, flightStopsDesc, flightsFare);
		
		return excelData;
		
	}
	
	//take screenshot of individual flights 
	public void takingScreenshot(ExtentTest logger) throws InterruptedException {
		
		Thread.sleep(1000);
		scrollPage(sortByPrices);
		Thread.sleep(1000);
		List<WebElement> details= individualDetails;
		
		
		if(details.get(0).getText().equalsIgnoreCase("Book Now")) {
			details.get(0).click();
			Thread.sleep(2000);
			windowHandlesSet();
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("firstFlight");
			Thread.sleep(2000);
			driver.close();
			logger.pass("Upcoming flight no.1 on coming Friday",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("firstFlight") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		WindowHandle(0);
		Thread.sleep(2000);
		}
		else {
			details.get(0).click();
			try {
				Thread.sleep(2000);
				takeScreenshotWhenPassed("firstFlight");
				Thread.sleep(2000);
				
				logger.pass("Upcoming flight no.1 on coming Friday",
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("firstFlight") + "")
								.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(details.get(1).getText().equalsIgnoreCase("Book Now")) {
			details.get(1).click();
			Thread.sleep(2000);
			windowHandlesSet();
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("secondFlight");
			Thread.sleep(2000);
			driver.close();
			logger.pass("Upcoming flight no.2 on coming Friday",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("secondFlight") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		WindowHandle(0);
		Thread.sleep(2000);
		}
		else {
			details.get(1).click();
			try {
				Thread.sleep(2000);
				takeScreenshotWhenPassed("secondFlight");
				Thread.sleep(2000);
				
				logger.pass("Upcoming flight no.2 on coming Friday",
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("secondFlight") + "")
								.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(details.get(2).getText().equalsIgnoreCase("Book Now")) {
			details.get(2).click();
			Thread.sleep(2000);
			windowHandlesSet();
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("thirdFlight");
			Thread.sleep(2000);
			driver.close();
			logger.pass("Upcoming flight no.3 on coming Friday",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("thirdFlight") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		WindowHandle(0);
		Thread.sleep(2000);
		}
		else {
			details.get(2).click();
			try {
				Thread.sleep(2000);
				takeScreenshotWhenPassed("thirdFlight");
				Thread.sleep(2000);
				
				logger.pass("Upcoming flight no.3 on coming Friday",
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("thirdFlight") + "")
								.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(details.get(3).getText().equalsIgnoreCase("Book Now")) {
			details.get(3).click();
			Thread.sleep(2000);
			windowHandlesSet();
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("fourthFlight");
			Thread.sleep(2000);
			driver.close();
			logger.pass("Upcoming flight no.4 on coming Friday",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("fourthFlight") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		WindowHandle(0);
		Thread.sleep(2000);
		}
		else {
			details.get(3).click();
			try {
				Thread.sleep(2000);
				takeScreenshotWhenPassed("fourthFlight");
				Thread.sleep(2000);
				
				logger.pass("Upcoming flight no.4 on coming Friday",
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("fourthFlight") + "")
								.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(details.get(4).getText().equalsIgnoreCase("Book Now")) {
			details.get(4).click();
			Thread.sleep(2000);
			windowHandlesSet();
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("fifthFlight");
			Thread.sleep(2000);
			driver.close();
			logger.pass("Upcoming flight no.5 on coming Friday",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("fifthFlight") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		WindowHandle(0);
		Thread.sleep(2000);
		}
		else {
			details.get(4).click();
			try {
				Thread.sleep(2000);
				takeScreenshotWhenPassed("fifthFlight");
				Thread.sleep(2000);
				
				logger.pass("Upcoming flight no.5 on coming Friday",
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("fifthFlight") + "")
								.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
}

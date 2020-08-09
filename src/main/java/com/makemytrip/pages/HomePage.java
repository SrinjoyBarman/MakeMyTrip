package com.makemytrip.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.makemytrip.base.MakeMyTripBase;




public class HomePage extends MakeMyTripBase{
	
	public HomePage(String path) {
		super(path);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	/****************All @FindBy annotations are derived from PageFactory class***************/
	
	@FindBy(xpath="//div[@class='loginModal displayBlock modalLogin dynHeight personal ']")
	WebElement block;
	
	@FindBy(xpath="//label[@for='toCity']")
	WebElement destination;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/input")
	WebElement destTextBox;
	
	@FindBy(xpath="//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/div[1]/p[contains(text(),'Kolkata, India')]")
	WebElement kolkata;
	
	@FindBy(xpath="//div[@class='DayPicker-Months']/div/div[3]/div/div[6]/div/p[1]")
	List<WebElement> fridays;
	
	@FindBy(xpath="//div[@class='DayPicker-Months']/div[2]/div[3]/div/div[6]/div/p[1]")
	List<WebElement> fridays2;
	
	@FindBy(xpath="//label[@for='travellers']")
	WebElement travellers;
	
	@FindBy(xpath="//div[@class='appendBottom20']/ul[1]/li")
	List<WebElement> adults;
	
	@FindBy(xpath="//div[@class='makeFlex column']/ul/li")
	List<WebElement> children;
	
	@FindBy(xpath="//*[@id=\"smallErrorMessage\"]")
	WebElement errMsg;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[5]/div[1]/button")
	WebElement applyBtn;
	
	@FindBy(xpath="//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")
	WebElement searchBtn;
	
	@FindBy(xpath="//p[@class='code']")
	WebElement friday;
	
	@FindBy(id="webklipper-publisher-widget-container-notification-frame")
	WebElement popup;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div/a/i")
	WebElement close;
	
	@FindBy(xpath="//p[@class='error-title']")
	WebElement noFlights;
	
	//assert title of home page
	public String assertTitle() {
		return driver.getTitle();
	}
	
	//enter the details of flights to be found and return if friday is clicked
	public String enterDetails(ExtentTest logger) throws InterruptedException {
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
		
//		block.click();
		ExplicitWait(destination);
		click(destination);
		
		logger.log(Status.INFO, "Click on the destination [TO] block");
		
		sendText(destTextBox,"dest");
	
		Thread.sleep(2000);
		arrowDown(destTextBox);
		
		logger.log(Status.INFO, "Enter the destination in the [TO] textbox");
		
		datePick(fridays,fridays2, "14");
		
		logger.log(Status.INFO, "Select the [DEPARTURE] date of next friday ");
		
		String foundDay= friday.getText();
		click(travellers);
		
		logger.log(Status.INFO, "Click on [TRAVELLERS & CLASS]");
		
		getAdults(adults,"adults");
		
		logger.log(Status.INFO, "Click on the number passed on by the properties file as to the number under Adults");
		
		click(applyBtn);
		
		logger.log(Status.INFO, "Click on [APPLY] button");
		
		ExplicitWait(searchBtn);
		click(searchBtn);
		
		logger.log(Status.INFO, "Click on [SEARCH] button");
		
//		Thread.sleep(4000);
		
		return foundDay;
	}
	
	public String enterInvalidDetails(ExtentTest logger) throws InterruptedException {
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
		
//		block.click();
		
		ExplicitWait(destination);
		click(destination);
		
		logger.log(Status.INFO, "Click on the destination [TO] block");
		
		sendText(destTextBox,"dest");
	
		Thread.sleep(2000);
		arrowDown(destTextBox);
		
		logger.log(Status.INFO, "Enter the destination in the [TO] textbox");
		
		String day= selectDay();
		
		datePick(fridays,fridays2, day);
		
		logger.log(Status.INFO, "Select the [DEPARTURE] date of next friday ");
		
//		String foundDay= friday.getText();
		click(travellers);
		
		logger.log(Status.INFO, "Click on [TRAVELLERS & CLASS]");
		
		getAdults(adults,"adults");
		
		logger.log(Status.INFO, "Click on the number passed on by the properties file as to the number under Adults");
		
		getAdults(children,"children");
		
		logger.log(Status.INFO, "Click on the number passed on by the properties file as to the number under Children");
		
		click(applyBtn);
		
		logger.log(Status.INFO, "Click on [APPLY] button");
		
		String err= errMsg.getText();
		
//		Thread.sleep(4000);
		
		return err;
	}
	
	public String takeScreenshot(ExtentTest logger) {
		
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("error1");
			Thread.sleep(2000);
			
			logger.fail("Passenger error",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("error1") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getAdults(children, "nochildren");
		
		logger.log(Status.INFO, "Change no. of children to 0");
		
		click(applyBtn);
		
		logger.log(Status.PASS, "Click on [APPLY] button");
		
		ExplicitWait(searchBtn);
		click(searchBtn);
		
		logger.log(Status.INFO, "Click on [SEARCH] button");
		
		ExplicitWait(noFlights);
		
		try {
			Thread.sleep(2000);
			takeScreenshotWhenPassed("error2");
			Thread.sleep(2000);
			
			logger.error("No Flights error",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty("error2") + "")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return noFlights.getText();
	}
}

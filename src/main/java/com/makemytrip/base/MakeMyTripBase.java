package com.makemytrip.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.makemytrip.utils.ExcelUtilsMakeMyTrip;
import com.makemytrip.utils.ExtentReportManager;

public class MakeMyTripBase extends ExcelUtilsMakeMyTrip {
	public static WebDriver driver;
	public static Properties prop = null;

	// calling Extent Reports from ExtentReportManager class
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public ExtentTest logger1;

	public MakeMyTripBase(String path) {
		super(path);
	}

	// invoke browser and setup property file by calling the setup class
	public void invokeBrowser(String browserName, String port) throws MalformedURLException {

		prop = MakeMyTripSetup.propertySet();
		driver = MakeMyTripSetup.getWebDriver(browserName, port);
	}

	// get Url
	public void getUrl(String urlKey) {
		driver.get(prop.getProperty(urlKey));
	}

	// function to hover
	public void moveToElement(By xpathKey) {
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(xpathKey)).build().perform();
	}

	// function to click
	public static void click(WebElement xpathKey) {
		xpathKey.click();
	}

	// function to select by visible text
	public void selectManufacturer(By xpathKey, String text) {
		Select s = new Select(driver.findElement(xpathKey));
		s.selectByVisibleText(prop.getProperty(text));
	}

	// get data of first three flights
	public String[][] getPrices(List<WebElement> flightNamekey, List<WebElement> flightNumKey,
			List<WebElement> depCitykey, List<WebElement> depTimekey, List<WebElement> arrCitykey,
			List<WebElement> arrTimekey, List<WebElement> flightDurkey, List<WebElement> flightStopsDesckey,
			List<WebElement> flightsFarekey) {
		try {
			List<WebElement> flightName = flightNamekey;
			List<WebElement> flightNum = flightNumKey;
			List<WebElement> depCity = depCitykey;
			List<WebElement> depTime = depTimekey;
			List<WebElement> arrCity = arrCitykey;
			List<WebElement> arrTime = arrTimekey;
			List<WebElement> flightDur = flightDurkey;
			List<WebElement> flightStopsDesc = flightStopsDesckey;
			List<WebElement> flightsFare = flightsFarekey;

			String[][] write = new String[5][9];

			int j = 0;
			int k = 0;
			for (int i = 0; i < 5; i++) {

				write[j][0] = flightName.get(i).getText();
				write[j][1] = flightNum.get(i).getText();
				write[j][2] = depCity.get(i).getText();
				write[j][3] = depTime.get(i).getText();
				write[j][4] = arrCity.get(i).getText();
				write[j][5] = arrTime.get(i).getText();
				write[j][6] = flightDur.get(i).getText();
				write[j][7] = flightStopsDesc.get(i).getText();
				write[j][8] = flightsFare.get(k).getText();
				j = j + 1;
				k = k + 1;
			}

			return write;
		} catch (

		Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// function to take screenshot
	public void takeScreenshotWhenPassed(String fileKey) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				System.getProperty("user.dir") + "\\screenshots\\" + prop.getProperty(fileKey) + "");
		FileUtils.copyFile(source, destination, true);

	}

	public String takeScreenshotWhenFailed() throws IOException {

		Date date = new Date();
		String currdate = new SimpleDateFormat("dd-MM-yy_hh-MM-ss").format(date);
		String path = System.getProperty("user.dir") + "//screenshots//";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(path + currdate + ".png");
		FileUtils.copyFile(source, destination, true);

		return path + currdate + ".png";
	}

	// function to explicit wait
	public void ExplicitWait(WebElement xpathKey) {
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.visibilityOf(xpathKey));
	}
	// get present date
	public String selectDay() throws InterruptedException {
		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String day = new SimpleDateFormat("dd").format(date);
		return day;
	}
	public String selectmonth() throws InterruptedException {
		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String day = new SimpleDateFormat("MM").format(date);
		return day;
	}

	public void datePicktwo(By xpathKey, int j) {
		List<WebElement> fridays = driver.findElements(xpathKey);
		fridays.get(j).click();
	}

	// pick date of coming friday
	public void datePick(List<WebElement> xpathKey, List<WebElement> xpathKey1, String day)
			throws NumberFormatException, InterruptedException {
		int flag = 0;
//		String day;
		List<WebElement> fridays = xpathKey;
		List<WebElement> fridays1 = xpathKey1;
		System.out.println(selectmonth());
//		if((day.equals("31"))&&selectmonth().equals("01")||selectmonth().equals("03")||selectmonth().equals("05")||selectmonth().equals("07")||selectmonth().equals("08")||selectmonth().equals("10")||selectmonth().equals("12")) {
//			day="1";
//		}
//		else if((day.equals("30"))||(day.equals("28"))||(day.equals("29")))
//			day="1";
		
		if(Integer.parseInt(day)>28)
			day="1";
		
		System.out.println(day);
		for (int i = 0; i < fridays.size(); i++) {

			if (Integer.parseInt(fridays.get(i).getText()) > Integer.parseInt(day)) {
				fridays.get(i).click();
				flag = 1;
				break;
			}
		}

		if (flag == 0)
			fridays1.get(0).click();
	}

	// function to scroll page
	public void scrollPage(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	// function to get top usedCars
	public List<WebElement> getTopUsedCars(By xpathName) {
		List<WebElement> carNames = driver.findElements(xpathName);
		return carNames;
	}

	// click to open in new tab
	public void clickToOpenInNewTab(By xpathKey) {

		Actions a = new Actions(driver);
		a.keyDown(Keys.CONTROL).click(driver.findElement(xpathKey)).keyUp(Keys.CONTROL).build().perform();

	}

	// handle traversal through windows by index
	public void WindowHandle(int handle) {
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(handle));
	}

	// handle traversal of windows through iterator
	public void windowHandlesSet() {
		String parent= driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String child: handles) {
			if(!parent.equalsIgnoreCase(child))
				driver.switchTo().window(child);
		}
	}

	// sendKeys
	public void sendText(WebElement xpathKey, String text) {
		xpathKey.sendKeys(prop.getProperty(text));
	}
	
	public void sendTextFromExcel(WebElement xpathKey, String sheetname, int rownum, String colname) {
		xpathKey.sendKeys(getCellData(sheetname,rownum,colname));
	}

	// arrowdown and backspace
	public void arrowDown(WebElement xpathKey) throws InterruptedException {
		xpathKey.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE);
		Thread.sleep(2000);
		xpathKey.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	// get traveller- adults and click
	public void getAdults(List<WebElement> xpathKey, String number) {
		List<WebElement> adults = xpathKey;

		for (int i = 0; i < adults.size(); i++) {
			if (adults.get(i).getText().equals(prop.getProperty(number)))
				adults.get(i).click();
		}
	}
	
	// function to quit
	public void close() {
		driver.quit();
	}
}

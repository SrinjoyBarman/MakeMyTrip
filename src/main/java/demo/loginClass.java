package demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.makemytrip.utils.ExcelUtilsMakeMyTrip;


public class loginClass extends ExcelUtilsMakeMyTrip{
	public loginClass() {
		super(System.getProperty("user.dir")+"\\MakeMyTrip_manual_testing_Final.xlsx");
		// TODO Auto-generated constructor stub
	}

	public static WebDriver driver;

	public void Output() throws Exception {
		driver = DriverSetup.getWebDriver();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		
		driver.get("http://localhost:8080/job/Make%20My%20Trip%20Test/51/console");
		
		driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("Srinjoy");
		driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("Vitalism@part1");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
		String output=driver.findElement(By.xpath("//pre[@class='console-output']")).getText();
		Date date = new Date();
		String currdate = new SimpleDateFormat("dd-MM-yy_hh:MM:ss").format(date);
		addColumn("JenkinsOutput",currdate);
		String[][] s= new String[1][1];
		s[0][0]=output;
		setJenkinsData("JenkinsOutput",s);
		driver.close();
	}
	
	public static void main(String[] args)throws Exception{
		loginClass lc= new loginClass();
		lc.Output();
	}
}

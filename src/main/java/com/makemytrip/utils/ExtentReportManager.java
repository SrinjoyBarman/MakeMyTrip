package com.makemytrip.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class ExtentReportManager {
//		public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;

	
	
	public static ExtentReports getReportInstance() {

		if (report == null) {

//				String reportName= DateUtils.getDate()+".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//MakeMyTripExtentReports.html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment Variables", "[jdk.1.8]");
			report.setSystemInfo("Framework", "Maven- Hybrid[Data Driven]");
			report.setSystemInfo("Runner", "TestNG");
			report.setSystemInfo("Tester Name", "# anchor a class");
			
			
			
			htmlReporter.config().setDocumentTitle("MakeMyTrip Application");
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setReportName("All MakeMyTrip Tests");
			htmlReporter.config().setTimeStampFormat("dd-MMMM-yyyy HH-mm-ss");
			
		}
		return report;
	}
}

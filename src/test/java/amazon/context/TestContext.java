package amazon.context;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import amazon.managers.PageObjectManager;
import amazon.report.ExtentManager;


public class TestContext {
	
	ExtentReports report;
	ExtentTest test;
	
	private PageObjectManager pageObjectManager;

	public TestContext() {
		System.out.println("Constructor TestContext");
		report=ExtentManager.getReports();
		this.pageObjectManager=new PageObjectManager();  //initialised once
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public void createScenario(String scenarioName)
	{
		test=report.createTest(scenarioName);
		this.pageObjectManager.getWebDriverManager().init(test);;
	}
	
	public void endScenario() {
		report.flush();
	}
	
	public void log(String msg) {
		System.out.println(msg);
		this.pageObjectManager.getWebDriverManager().log(msg);
	}
	
	public void logFailure(String msg) {
		System.out.println("FAILURE --"+msg);
		test.log(Status.FAIL,msg);
		Assert.fail(msg);
	}
	
}

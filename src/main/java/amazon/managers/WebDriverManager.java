package amazon.managers;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebDriverManager {

	WebDriver driver;
	ExtentTest test;
	Properties prop;
	SoftAssert softAssert;
	
	public WebDriverManager() {
		//init the properties file here
		try {	
		
		 prop=new Properties();
			String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
		    FileInputStream fs=new FileInputStream(path);
			prop.load(fs);
				
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		softAssert=new SoftAssert();
	}
	
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	
	public void init(ExtentTest test) {
		this.test=test;
	}
	
	public void openBrowser(String browser) {
		test.log(Status.INFO,"Open Browser "+browser);
		 if(getProperty("grid_run").equals("yes")) {
	        	// run on grid
	        	DesiredCapabilities cap=null;	        	
			   if(browser.equals("Chrome")){
					 cap = DesiredCapabilities.chrome();
					 cap.setBrowserName("chrome");
					 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}				
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				}else {
	       
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		if(browser.equals("Mozilla")) {
			driver=new FirefoxDriver();			
		}else if(browser.toLowerCase().equals("chrome")) {
			//driver=new ChromeDriver();	
			ChromeOptions options = new ChromeOptions();		    
		    options.addArguments("--start-maximized");
		    driver =new ChromeDriver(options);
	
		}else if(browser.equals("Edge"))
		{
			driver=new EdgeDriver();		
		}
	        }
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	//Launch Url 
	public void navigate(String urlKey) {
		test.log(Status.INFO,"Launch Url "+getProperty(urlKey));
		driver.get(getProperty(urlKey));
		String title=driver.getTitle();
		if(!compareText(getProperty("title"), title))
		{
			logFailure("Application is not launched ",true);
		}else {
			logPass("Application successfully launched with title -->"+title);
		}
			
	}
	
	public String replaceText(String replacableValue, String replaceValue) {
		replacableValue=replacableValue.replace("text", replaceValue);
		return replacableValue;
	}
	
	public void scrollToElementView(WebElement element) {
		//WebElement Element=driver.findElement(getLocator(locatorKey));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/*
	 * public void scrollToElementView(String locatorKey) { WebElement
	 * element=driver.findElement(getLocator(locatorKey)); JavascriptExecutor js =
	 * (JavascriptExecutor) driver; // Scrolling down the page till the element is
	 * found js.executeScript("arguments[0].scrollIntoView();", element); }
	 */
	
	
	//to perform Scroll on application using Selenium	
	 public void scrollToElementView() {
		 JavascriptExecutor executor = (JavascriptExecutor) driver;		
	    executor.executeScript("window.scrollBy(0,350)", "");
	}
	
	public void iterateAndScrolltoView(String locatorKey,String expectedValue) {
		List<WebElement> listOfElements=driver.findElements(getLocator(locatorKey));
		for(WebElement element:listOfElements) {
			String text=element.getText().trim();
			if(text.equals(expectedValue)) {
				scrollToElementView(element);
				break;
			}
		}
	}
	public void iterateAnClick(String locatorKey,String expectedValue) {
		List<WebElement> listOfElements=driver.findElements(getLocator(locatorKey));
		for(WebElement element:listOfElements) {
			if(element.getText().trim().equals(expectedValue)) {
				element.click();
				break;
			}
		}
	}
	
	//click on expected array, ie., nth element from array (array start from 1 )
	public void clickOnExpectedItem(String locatorKey,int index) {
		List<WebElement> listOfElements=driver.findElements(getLocator(locatorKey));		
		listOfElements.get(index-1).click();
	}
	
	//this metod will switch to new window. however if there is scenario of multiple window then this code can be tweaked 
	public void switchToDifferentWindow() {
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
		String child_window=I1.next();
		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);
		 String newWindow=driver.switchTo().window(child_window).getTitle();
		 System.out.println("Usee is in new window "+newWindow);
		}}
	}
	
	//Click on element
	public void click(String locatorKey) {
		
		test.log(Status.INFO,"Click on locator "+getLocator(locatorKey));
		driver.findElement(getLocator(locatorKey)).click();
	}
	//Click on element	
	public void click(String locator ,String replaceText) {
			String replaced_xpath=getProperty(locator).replace("replace_text", replaceText);
			test.log(Status.INFO,"Click on locator "+replaced_xpath);
			WebElement element=driver.findElement(By.xpath(replaced_xpath));
			scrollToElementView(element);
			element.click();
		}
	//javascript click
	public void javasciptclick(String locatorKey) {
		    WebElement element=driver.findElement(getLocator(locatorKey));
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", element);
	}
	//Enter key on element
	public void type(String locatorKey,String data) {
		test.log(Status.INFO,"Type data in Locator "+getLocator(locatorKey));
		driver.findElement(getLocator(locatorKey)).sendKeys(data);
	}
	
	//get element Text
	public String getElementText(String locatorKey) {
		String text=driver.findElement(getLocator(locatorKey)).getText();
		return text;
	}
	
	
	/***************Validation Functions *****************************************************/
	public void compareTwoText(String expectedValue, String actualValue) {
	
		try {
			Assert.assertEquals(actualValue, expectedValue);
		} catch (AssertionError e) {
		    System.out.println(expectedValue+" Value is not visible "+e.getMessage());
		    
		}
	}
	
	       // presence and visibility
			public boolean isElementPresent(String locatorKey) {
				By locator = getLocator(locatorKey);
				
				try {
				  // present and visible
				  WebDriverWait wait = new WebDriverWait(driver,10);
				  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				 
				}catch(Exception ex) {
					return false;
				}
				return true;
			}
			
			/*****************************************************************************************/
	
	public boolean compareText(String expectedValue, String actualValue) {		
		if(expectedValue.equals(actualValue))
			return true;
		else
			return false;
	}
    public void logFailure(String msg, boolean stopExecution) {
    	System.out.println("FAILURE---- "+ msg);
    	// screenshot of the page and embedd in reports
    	// fail in extent reports
    	test.log(Status.FAIL, msg);
    	// fail in testng-cucumber
    //	Assert.fail(msg);// hard assertion
    	softAssert.fail(msg);
    	
    	if(stopExecution)
    		softAssert.assertAll();
    }
    
    public void logPass(String msg) {
    	System.out.println("Passed---- "+ msg);
    	test.log(Status.PASS, msg);
    }

	
	public By getLocator(String locatorKey) {
		if(locatorKey.endsWith("_id")) {
			return By.id(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_xpath")) {
			return By.xpath(prop.getProperty(locatorKey));
		}else
			return By.cssSelector(prop.getProperty(locatorKey));
	}
	
	
		
		public void quit() {
			
			if(driver !=null)
				driver.quit();
			
			if(softAssert!=null)
				softAssert.assertAll();
			
		}
			
	
	//log the report statement or info
	public void log	(String msg) {
		System.out.println(msg);
		test.log(Status.INFO, msg);
	}
	
	
}

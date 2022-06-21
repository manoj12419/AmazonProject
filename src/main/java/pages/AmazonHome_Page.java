package pages;

import amazon.managers.WebDriverManager;

public class AmazonHome_Page {

	WebDriverManager app;

	
	public AmazonHome_Page(WebDriverManager app) {
		this.app=app;
	}
    
	
	public void launchBrowser(String browser) {
		app.log("Launched "+browser+" Browser");
		app.openBrowser(browser);
		app.navigate("url_key");
		
	}
	
	public void clickOnHamburgerMenu() {
		app.click("hamburgermenu_xpath");	
	}
	
	public void scrolltoElementandClickOnExpectedTab(String mainTab,String subTab) {	
		 
		 app.click("subtab_xpath",subTab);
	     app.log("Clicked on "+subTab+" Under "+mainTab);
	     
	}
	
	public void clickOnexpectedDepartment(String brand) {
		app.iterateAndScrolltoView("tv_department_xpath","Brands");
		app.click("subdepartment_xpath",brand);
		
	}
}

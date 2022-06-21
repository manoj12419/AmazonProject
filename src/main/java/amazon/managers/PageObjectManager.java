package amazon.managers;
import pages.AmazonHome_Page;
import pages.AmazonTelevision_Page;

public class PageObjectManager {

	
	AmazonHome_Page homepage;
	AmazonTelevision_Page tvpage;
	WebDriverManager webdrivermanager;
	
	public PageObjectManager() {
		this.webdrivermanager=new WebDriverManager();
	}
	
	public WebDriverManager getWebDriverManager() {
		return webdrivermanager;
	}
	
	public AmazonHome_Page getHomePage() {
		if(homepage ==null) 
			homepage=new AmazonHome_Page(webdrivermanager);
		return homepage;
	}
	
	public AmazonTelevision_Page getTVPage() {
		if(tvpage ==null) 
			tvpage=new AmazonTelevision_Page(webdrivermanager);
		return tvpage;
	}
	
	
}

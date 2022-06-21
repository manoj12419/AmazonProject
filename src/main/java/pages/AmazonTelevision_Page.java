package pages;

import amazon.managers.WebDriverManager;

public class AmazonTelevision_Page {

	
     WebDriverManager app;
	
	public AmazonTelevision_Page(WebDriverManager app) {
		this.app=app;
	}
	
	public void verifyTheSelectedTvItem() {
		
	}
	
	public void sortTheItem(String sortOrder) {
		app.javasciptclick("sortby_xpath");
		app.iterateAnClick("listofSortItems_xpath", sortOrder);
		
	}
	
	public void clickOnSecondHighestPriceItem(int sortOrder) {
		app.clickOnExpectedItem("pricelist_items_xpath", sortOrder);		
	}
	
	public void verifyTheSelectedItemHasExpectedValue(String expectedValue) {
		app.switchToDifferentWindow();
		String actualValue=app.getElementText("aboutme_xpath");
	//	Boolean isMatched=app.compareText(expectedValue, actualValue+" test");
		if(!app.compareText(expectedValue, actualValue))
		{
			app.logFailure("Expected Text is missing",true);
		}else {
			app.logPass(expectedValue+" is exists in application on clicking the tv ");
		}
		
		
	}
	
}

package steps;

import java.util.List;
import java.util.Map;

import amazon.context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import item.Brand;
import pages.AmazonHome_Page;
import pages.AmazonTelevision_Page;

public class BuyTv {

	public TestContext context;
	public AmazonHome_Page homepage;
	public AmazonTelevision_Page tvpage;
	public BuyTv(TestContext context) {
		this.context=context;
		this.homepage=this.context.getPageObjectManager().getHomePage();
		this.tvpage=this.context.getPageObjectManager().getTVPage();
	}
	
	@Given("I want to lunch <url> website in <browser> browser")
	public void i_want_to_lunch_url_website_in_browser_browser(DataTable dataTable) {
		List<Map<String,String>> data=dataTable.asMaps();
		homepage.launchBrowser(data.get(0).get("browser"));	
		context.log("Lunach url ");
		
	}

	

@Given("Click on Hamburger menu in the top left corner")
public void click_on_hamburger_menu_in_the_top_left_corner() {
    // Write code here that turns the phrase above into concrete actions
	homepage.clickOnHamburgerMenu();
	context.log("Click on hamburger menu");
}

@When("Scroll down and then Click on the {string} link under {string} section")
public void scroll_down_and_then_click_on_the_link_under_section(String maintab, String subTab) {
	homepage.scrolltoElementandClickOnExpectedTab(subTab,maintab);
}

@Then("Click on {string} under {string} sub menu")
public void click_on_under_sub_menu(String maintab, String subTab) {
	homepage.scrolltoElementandClickOnExpectedTab(subTab,maintab);
}


@Then("Scroll down and filter the results by <Brand>")
public void scroll_down_and_filter_the_results_by_brand(List<Brand> brand) {
     homepage.clickOnexpectedDepartment(brand.get(0).name);
	context.log("brand to select "+brand.get(0).name);
}

@DataTableType
public Brand entry(Map<String, String> entry) {

	return new Brand(entry.get("Brand"));
}




@Then("Click on {string} under {string} sub section")
public void click_on_under_sub_section(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
   // throw new io.cucumber.java.PendingException();
}


@Then("Sort the Samsung results with {string}")
public void sort_the_samsung_results_with(String sortOrder) {
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.java.PendingException();
	tvpage.sortTheItem(sortOrder);
}


@Then("Click on the {int} nth highest priced item")
public void click_on_the_nd_highest_priced_item(Integer order) {
	tvpage.clickOnSecondHighestPriceItem(order);
}
@Then("Veify the {string} section is present for selected Item")
public void veify_the_section_is_present_for_selected_item(String string) {
   tvpage.verifyTheSelectedItemHasExpectedValue(string);
  
	
}

@Before
public void init(Scenario s) {
	System.out.println("-------------INIT---------------- "+ s.getName());
}

@After
public void quit(Scenario s) {
	System.out.println("--------------QUIT---------------"+ s.getName());
}


}

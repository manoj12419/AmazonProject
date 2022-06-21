package steps;

import amazon.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	public TestContext context;
	
	public Hooks(TestContext context) {
		this.context=context;		
	}
	
	@Before
	public void before(Scenario scenario) {
		context.createScenario(scenario.getName());
		context.log("Starting scenario "+ scenario.getName());
		
	}
	
	@After
	public void after(Scenario scenario) {
	   context.endScenario();
	   context.getPageObjectManager().getWebDriverManager().quit();
		System.out.println("-------------------------------------------------");
	}
}

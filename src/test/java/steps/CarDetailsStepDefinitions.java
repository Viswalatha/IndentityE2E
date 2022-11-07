package steps;

import Pages.ValueMyCarPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;


public class CarDetailsStepDefinitions {
    WebDriver driver;
    ValueMyCarPage valueMyCarPage = new ValueMyCarPage(driver);

    @Before
    public void init(){
        driver = new ChromeDriver();
    }

    @Given("^go to the carzoo website$")
        public void gotoCarzoo(){
        driver.get("https://www.cazoo.co.uk/value-my-car/");
    }

    @Then("^get reg numbers and verify make and model on text file through carzoo site$")
    public void verifyCarDetailsTest() throws IOException {
        valueMyCarPage.getRegAndverifyCarDetails(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}

package Pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CarDetails;
import java.io.IOException;
import java.time.Duration;


public class ValueMyCarPage {

    CarDetails carDetails = new CarDetails();
    
    WebDriver driver;

    public ValueMyCarPage (WebDriver driver){
    this.driver = driver;
    }

    By acceptCookies = By.xpath("//span[contains(text(),'Accept All')]");

    By enterReg = By.id("vrm");

    By startValuation = By.xpath("//button[@type='submit']");

    By makeAndModel = By.xpath("//*[contains(text(),'Make/model')]");

    By errorMessage = By.xpath("//*[@id='your-registration-number-form']/div/div[1]/span");


    public void getRegAndverifyCarDetails(WebDriver driver) throws IOException {

        driver.findElement(acceptCookies).click();

        for (int i = 0; i < carDetails.extractRegNumbers().size(); i++) {
            if (i == 0) {
                driver.findElement(enterReg).sendKeys(carDetails.extractRegNumbers().get(i));
                driver.findElement(startValuation).click();
            } else {
                driver.navigate().back();
                driver.findElement(enterReg).clear();
                driver.findElement(enterReg).sendKeys(carDetails.extractRegNumbers().get(i));
                driver.findElement(startValuation).click();
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            String carReg = carDetails.extractRegNumbers().get(i);
            for(int k = 0; k < carDetails.expetedCarDetails().size(); k++){
                if(carDetails.expetedCarDetails().contains(carReg))
                    if(wait.until(ExpectedConditions.visibilityOfElementLocated(makeAndModel)).isDisplayed() ) {
                        Assert.assertTrue("Car make not found or not equal", driver.findElement(makeAndModel).getText().contains(carDetails.expetedCarDetails().get(k).getMakeAndModel()));
                    }
                    else{
                        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed());
                    }
                }

            }


        }

    }



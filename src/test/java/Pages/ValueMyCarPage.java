package Pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.CarDetails;
import java.io.IOException;

public class ValueMyCarPage{

    CarDetails carDetails = new CarDetails();
    
    WebDriver driver;

    public ValueMyCarPage (WebDriver driver){
    this.driver = driver;
    }

    By acceptCookies = By.xpath("//span[contains(text(),'Accept All')]");

    By enterReg = By.id("vrm");

    By startValuation = By.xpath("//button[@type='submit']");

    By getRegNumber = By.xpath("//*[@id='main-content']/div/div[2]/div/div/div/div[1]/div[1]/div/div/div[1]/div[2]/p");

    By makeAndModel = By.xpath("//*[@id='main-content']/div/div[2]/div/div/div/div[1]/div[1]/div/div/div[1]/div[2]/p[2]");

    By errorMessage = By.xpath("//*[@id='your-registration-number-form']/div/div[1]/span");


    public void getRegAndverifyCarDetails(WebDriver driver) throws IOException, InterruptedException {

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
            Thread.sleep(3000);
            String carReg = carDetails.extractRegNumbers().get(i);
            switch (carReg) {
                case "AD58 VNF":
                    Assert.assertTrue("Car reg number not found or not equal",driver.findElement(getRegNumber).getText().contains(carDetails.expetedCarDetails().get(2).getRegistration()));
                    Assert.assertTrue("Car make not found or not equal", driver.findElement(makeAndModel).getText().contains(carDetails.expetedCarDetails().get(2).getMakeAndModel()));
                    break;
                case "BW57 BOW":
                    Assert.assertTrue("Car reg number not found ",driver.findElement(errorMessage).isDisplayed());
                    break;
                case "KT17DLX":
                    Assert.assertTrue("Car reg number not found or not equal",driver.findElement(getRegNumber).getText().contains(carDetails.expetedCarDetails().get(4).getRegistration()));
                    Assert.assertTrue("Car make not found or not equal", driver.findElement(makeAndModel).getText().contains(carDetails.expetedCarDetails().get(4).getMakeAndModel()));
                    break;
                case "SG18 HTN":
                    Assert.assertTrue("Car reg number not found or not equal",driver.findElement(getRegNumber).getText().contains(carDetails.expetedCarDetails().get(1).getRegistration()));
                    Assert.assertTrue("Car make not found or not equal", driver.findElement(makeAndModel).getText().contains( carDetails.expetedCarDetails().get(1).getMakeAndModel()));
                    break;
                default:
                    System.out.println("Reg number " + carReg + "not found");
            }
        }

    }



}

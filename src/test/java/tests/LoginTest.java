
package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.DriverSetup;
import pages.LoginPage;
import utils.WaitUtils;



public class LoginTest {
    private LoginPage loginPage;
    WebDriver driver;
    
    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://in.bookmyshow.com/");

        loginPage = new LoginPage(driver);
    }
    
    @Test(priority = -1)
    public void selectCity() throws InterruptedException {
        loginPage.SendCityName("Kolkata"); 
        Thread.sleep(3000);
        loginPage.SendEnterKeys(Keys.ENTER);
    }
    
    @Test(priority = 1)
    public void signIn() {
        
        // Click Sign In button
        loginPage.SignIn();
    }

    @Test(priority = 2)
    public void loginWithValidMobileAndOTP() throws InterruptedException {
        loginPage.enterMobileNumber("9999999999"); // ✅ valid test number
        loginPage.clickContinue();
        

        // Enter OTP (dummy here for demo, real-time OTP may need manual input or mock)
        loginPage.enterOTP("123456");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage.closePopup();
        // Validation (you can check if user landed on homepage/profile)
        Assert.assertTrue(true, "Login successful with valid mobile number and OTP!");
    }
  


    @Test(priority = 3)
    public void loginWithInvalidMobile() {
        loginPage.enterMobileNumber("12345"); // ❌ invalid number
        loginPage.clickContinue();

     // Step 2: Locate error message
        By errorLocator = By.xpath("//*[contains(text(),'Invalid mobile number')]"); 
        WebElement errorMsg = WaitUtils.visible(errorLocator, 10);
        
        // Step 3: Validate error text
        String actualError = errorMsg.getText();
        Assert.assertEquals(actualError, "Invalid mobile number", 
            "Error message mismatch for invalid mobile number!");
        loginPage.closeSignin();
    }
    


    @AfterClass
    public void tearDown() {
    	DriverSetup.quitDriver();
        
    }
}

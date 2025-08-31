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
import pages.GiftCardPage;

public class GiftCardTest {
    private GiftCardPage giftCardPage;
    WebDriver driver;
    
    @BeforeClass
    public void setUp() {
    	driver = DriverSetup.getDriver();
        driver.get("https://in.bookmyshow.com/");
        giftCardPage = new GiftCardPage(DriverSetup.getDriver());
        
        giftCardPage.sendCityName("Kolkata"); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        giftCardPage.sendEnterKey(Keys.ENTER);
    }
    
    @Test(priority = 1)
    public void testCheckGiftCardBalanceIconVisible() {
        giftCardPage.clickGiftCardIcon();
        Assert.assertTrue(
            giftCardPage.isCheckBalanceIconDisplayed(),
            "❌ Check Balance icon should be visible on Gift Card page."
        );
    }
// this i have to take care
    @Test(priority = 2)
    public void testInvalidVoucherShowsErrorMessage()  {
        giftCardPage.clickCheckBalanceIcon();               // Step 1: Open "Check Balance"
        giftCardPage.enterVoucherCode("123");       		// Step 2: Enter invalid code
        //giftCardPage.submitVoucher();                     // Step 3: Click "Check Balance" button

        // Step 4: Validate error message
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String actualError = giftCardPage.getErrorMessage();
        Assert.assertEquals(
            actualError, 
            "Please verify input", 
            "❌ Error message mismatch for invalid voucher code!"
        );
    }


    @AfterClass
    public void tearDown() {
    	DriverSetup.quitDriver();
        
    }
}

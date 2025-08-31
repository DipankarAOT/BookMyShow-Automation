package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.DriverSetup;
import pages.CitySearchPage;

public class CitySearchTest {
    WebDriver driver;
    
    CitySearchPage cityPage;
    
    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://in.bookmyshow.com/");

        cityPage=new CitySearchPage(driver);
        
        
    }
    
    @Test(priority=1)
    public void testValidCitySearch() throws InterruptedException {
 
    	cityPage.selectCity("Kolkata");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //cityPage.sendEnterKey(Keys.ENTER);
    	cityPage.citySearchClear();
    	
    }
    
    
    @Test(priority=2)
    public void testInvalidCitySearch() {
        cityPage.citySearchClear();

        cityPage.selectCity("Contai"); 
        
        
        Assert.assertTrue(cityPage.isInvalidCityMessageDisplayed(),
                "Expected 'No results found' message, but it was not displayed.");
    }
    
    @Test(priority=3)
    public void testViewAllCities() throws InterruptedException {
        cityPage.viewAllCities();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        cityPage.hideAllCities();
    }
    
    @AfterClass
    public void tearDown() {
    	DriverSetup.quitDriver();
        
    }

}   


































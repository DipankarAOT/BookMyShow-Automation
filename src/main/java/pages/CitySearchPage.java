package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import locators.LocatorRepository;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class CitySearchPage {
    private WebDriver driver;

    // -------- Locators --------
    // The actual BookMyShow DOM uses input with id="input-search-box"
    @FindBy(xpath = "//input[@id='dummy']")
    private WebElement citySearchBox;

    // List of city results in the suggestion dropdown
    @FindBy(xpath = "//div[@id='bottomSheet-model-close']")
    private List<WebElement> cityResults;

    // "No results found" message
    @FindBy(xpath = "//div[contains(text(),'No results found')]")
    private WebElement noResultsMessage;
    
    @FindBy(xpath = "//input[@placeholder='Search for your city']")
    private WebElement city;
    
    

    // -------- Constructor --------
    public CitySearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    //By searchBox = By.xpath("//input[@placeholder='Search for your city']");
    By cityOptions = By.xpath("//*[text()='View All Cities']");

    //WebDriver driver1;
    WebDriverWait wait;
    
    
    public void citySearchClear() {   // ensure focus

    	WebElement searchBox = driver.findElement(By.xpath ("//input[@placeholder='Search for your city']"));
    	searchBox.clear();  // should remove text
    	
    }


    public void sendEnterKey(Keys enterKey) {
        city.sendKeys(enterKey);
    }

    public void clickCity() {
        city.click();
    }
    // -------- Actions --------
    // Enter city name in search box
//    public void selectCity(String cityName) {
//        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(citySearchBox));
//        search.clear();
//        search.sendKeys(cityName);
//
//        // wait for list of cities to appear
//        List<WebElement> cities = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cityOptions));
//
//        for (WebElement city : cities) {
//            System.out.println("City option: " + city.getText());
//            if (city.getText().trim().equalsIgnoreCase(cityName)) {
//                wait.until(ExpectedConditions.elementToBeClickable(city)).click();
//                break;
//            }
//        }
//    }
    public void selectCity(String city) {
        WebElement input = WaitUtils.visible(LocatorRepository.get("cityInput"), 10);
        input.sendKeys(city);
    }

    public void viewAllCities() {
        WaitUtils.clickable(LocatorRepository.get("allCities"), 10).click();
        List<WebElement> cities = driver.findElements(LocatorRepository.get("otherCities"));
        System.out.println("Other cities count: " + cities.size());
    }

    // Validate and click city from dropdown
    public boolean validateAndClickCity(String expectedCity) {
        for (WebElement result : cityResults) {
            if (result.getText().equalsIgnoreCase(expectedCity)) {
                result.click();   // click the matching city
                return true;
            }
        }
        return false; // City not found
    }

    // Only validate if city exists in search results
    public boolean validateSearchResult(String expectedCity) {
        for (WebElement result : cityResults) {
            if (result.getText().equalsIgnoreCase(expectedCity)) {
                return true;
            }
        }
        return false;
    }

    // Check "No results found" message
    public boolean isInvalidCityMessageDisplayed() {
        try {
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public void hideAllCities() {
		WaitUtils.clickable(LocatorRepository.get("hideCities"), 10).click();
        //List<WebElement> cities = driver.findElements(LocatorRepository.get("otherCities"));
	}
}

package pages;

import base.DriverSetup;
import locators.LocatorRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WaitUtils;

public class MoviePage {
    private final WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Search for your city']")
    private WebElement city;


    public MoviePage() {
        this.driver = DriverSetup.getDriver();
        PageFactory.initElements(driver, this);
        System.out.println("MoviePage initialized with WebDriver instance.");
    }
    
    
 // City methods
    public void citySearchClear() {
        city.clear();
    }

    public void sendCityName(String cityName) {
        city.sendKeys(cityName);
    }

    public void sendEnterKey(Keys enterKey) {
        city.sendKeys(enterKey);
    }

    public void clickCity() {
        city.click();
    }

   

    public void openMoviesTab() {
        System.out.println("Opening 'Movies' tab.");
        WaitUtils.clickable(LocatorRepository.get("moviesTab"), 10).click();
    }
//not working
    public void movie8_selectAnyRunningMovieAndPrintTitle() {
        System.out.println("Attempting to select one of the first two running movies.");

        for (int i = 1; i <= 2; i++) {
            try {
                By tile = By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[" + i + "]");
                WebElement movie = WaitUtils.clickable(tile, 10);
                String name = movie.getText();
                System.out.println("Found movie tile #" + i + " with name: " + name);
                movie.click();

                Thread.sleep(1500);

                if (driver.getPageSource().contains("Sorry for bug-ging")) {
                    System.out.println("Bug page detected. Navigating back and trying next movie tile.");
                    driver.navigate().back();
                    WaitUtils.clickable(LocatorRepository.get("movieSelection"), 10);
                    continue;
                }

                WebElement title = WaitUtils.visible(By.xpath("//h1[@class='sc-qswwm9-6 ea-drWB']"), 10);
                System.out.println("Selected movie: " + title.getText());
                return;

            } catch (Exception e) {
                System.out.println("Exception while trying movie tile #" + i + ". Trying next if available.");
            }
        }

        Assert.fail("Could not open a valid movie card");
    }
    
    public void movie_selectAnyRunningMovieAndPrintTitle() {
        System.out.println("Attempting to select one of the first two running movies.");

        for (int i = 1; i <= 2; i++) {
            try {
                By tile = By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[" + i + "]");
                WebElement movie = WaitUtils.clickable(tile, 10);
                String name = movie.getText();
                System.out.println("Found movie tile #" + i + " with name: " + name);
                movie.click();

                Thread.sleep(1500);

                if (driver.getPageSource().contains("Sorry for bug-ging")) {
                    System.out.println("Bug page detected. Navigating back and trying next movie tile.");
                    driver.navigate().back();
                    WaitUtils.clickable(LocatorRepository.get("movieSelection"), 10);
                    continue;
                }

                WebElement title = WaitUtils.visible(By.xpath("//h1[@class='sc-qswwm9-6 ea-drWB']"), 10);
                System.out.println("Selected movie: " + title.getText());
                return;

            } catch (Exception e) {
                System.out.println("Exception while trying movie tile #" + i + ". Trying next if available.");
            }
        }

        Assert.fail("Could not open a valid movie card");
    }

    public void validateTopMenuNavigation() throws InterruptedException {
        System.out.println("Starting top menu navigation validation.");
        String[] menu = {
            "Movies", "Stream", "Events", "Plays", "Sports", "Activities",
            "ListYourShow", "Corporates", "Offers", "Gift Cards"
        };

        for (String m : menu) {
            System.out.println("Clicking on top menu item: " + m);
            By link = By.xpath("//a[text()='" + m + "']");
            WebElement el = WaitUtils.clickable(link, 10);
            el.click();
            Thread.sleep(1000); // Keeping it as in original logic
            System.out.println("Navigated to '" + m + "' and returning to previous page.");
            driver.navigate().back();
        }

        System.out.println("Top menu navigation validation completed.");
    }

    public void upcomingAndNowShowing() {
        System.out.println("Navigating to 'Upcoming' and 'Now Showing' sections in Movies.");
        openMoviesTab();
        WaitUtils.clickable(LocatorRepository.get("upcoming"), 10).click();
        System.out.println("'Upcoming' section clicked.");

        WaitUtils.clickable(LocatorRepository.get("nowShowing"), 10).click();
        System.out.println("'Now Showing' section clicked.");
    }

}
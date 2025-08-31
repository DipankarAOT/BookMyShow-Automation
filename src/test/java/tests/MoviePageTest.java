package tests;

import base.DriverSetup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.MoviePage;

public class MoviePageTest {

    private WebDriver driver;
    private MoviePage moviePage;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = DriverSetup.getDriver();
        driver.get("https://in.bookmyshow.com/");

        moviePage = new MoviePage();
        
        moviePage.sendCityName("Kolkata"); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        moviePage.sendEnterKey(Keys.ENTER);
    }
   
    /**
     * TC8 - Search for a currently running movie in a selected city and validate the search results
     */

    @Test
    public void testMovieSelectRunningMovie() {
        moviePage.movie_selectAnyRunningMovieAndPrintTitle();
    }

    
    //TC9 - Validate the UI in the screen
    @Test
    public void testTopMenuNavigation() throws InterruptedException {
        moviePage.validateTopMenuNavigation();
    }
    //Tc-10
    @Test
    public void testUpcomingAndNowShowing() {
        moviePage.upcomingAndNowShowing();
    }
    
    @AfterClass
    public void tearDown() {
    	DriverSetup.quitDriver();
        
    }
}


//@Test
//public void testSelectRunningMovie() {
//  moviePage.openMoviesTab();
//  moviePage.movie_selectAnyRunningMovieAndPrintTitle();
//
//
//  System.out.println(" TC8: Search currently running movie ====");
//  moviePage.movie_selectAnyRunningMovieAndPrintTitle();
//
//  // Basic validation - title element should be present
//  Assert.assertTrue(
//      driver.findElement(By.xpath("//h1[contains(@class,'sc-qswwm9-6')]")).isDisplayed(),
//      "Movie details page is not displayed!"
//  );
//}



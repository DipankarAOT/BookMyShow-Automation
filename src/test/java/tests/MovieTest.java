package tests;

import base.DriverSetup;
import org.testng.annotations.*;
import pages.CityPage;
import pages.MoviePage;

public class MovieTest extends DriverSetup {

    private CityPage city;
    private MoviePage movie;

    @BeforeMethod
    public void setupPagesAndSelectCity() {
        // Driver is already initialized by DriverSetup's @BeforeMethod
        city = new CityPage();
        movie = new MoviePage();

        // Select city before every test
        city.selectCity();
    }

    //@Test
//    public void testSelectRunningMovie() {
//        movie.openMoviesTab();
//        movie.movie_selectAnyRunningMovieAndPrintTitle();
//    }

    @Test
    public void testSelectRunningMovie() {
        movie.movie8_selectRunningMovie();
    }

    @Test
    public void testUpcomingAndNowShowing() {
        movie.upcomingMovie();
    }

    @Test
    public void testTopMenuNavigate() throws InterruptedException {
        movie.validateTopMenuNavigation();
    }
}
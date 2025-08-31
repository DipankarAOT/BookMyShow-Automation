package locators;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, By> locators = new HashMap<>();

    static {
        locators.put("moviesTab", By.xpath("//a[text()='Movies']"));
        locators.put("movieSelection", By.xpath("//div[@class='sc-7o7nez-0 lkwOqB']"));
        //locators.put("upcoming", By.xpath("//a[text()='Upcoming']"));
        locators.put("upcoming", By.xpath("//*[@alt=\"Coming Soon\"]"));
        ////*[@id="super-container"]/div[2]/div[3]/div[2]/div[2]/div/div/div[2]/a/div/div[2]/div/img
//        locators.put("nowShowing", By.xpath("//a[text()='Now Showing']"));
        locators.put("nowShowing", By.xpath("//*[@alt='Now Showing']"));
        locators.put("allCities", By.xpath("//p[text()='View All Cities']"));
        locators.put("hideCities", By.xpath("//p[text()='Hide all cities']"));
        locators.put("otherCities", By.xpath("//ul[@class='sc-yuf6si-1 idrZHM']//li"));
        locators.put("cityInput", By.xpath("//input[@placeholder='Search for your city']"));
      //p[text()='Hide all cities']"
    }

    public static By get(String key) {
        return locators.get(key);
    }
}

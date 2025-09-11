package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class DriverSetup {

    // Thread-safe WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static String getBrowserName() {
        return browserName.get();
    }
    @Parameters("browser")
    //@BeforeClass
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        WebDriver drv;

        switch (browser.toLowerCase()) {
            case "firefox":
                drv = new FirefoxDriver();
                break;
            case "edge":
                drv = new EdgeDriver();
                break;
            case "chrome":
            default:
                drv = new ChromeDriver();
                break;
        }

        drv.manage().window().maximize();
        drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        drv.get("https://in.bookmyshow.com"); 

        driver.set(drv);  
        browserName.set(browser);
        System.out.println(browser + " browser launched.");
    }

    @AfterMethod
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            browserName.remove();        
        }
    }
}
//package base;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class DriverSetup {
//	private static WebDriver driver;
//	
//	public static WebDriver getDriver() {
//        if (driver == null) {
//            driver = new ChromeDriver(); 
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//            // ✅ Always Chrome
//            driver.manage().window().maximize();
//        }
//        return driver;
//    }
//
//
////	
//	public static void quitDriver() {
//	    try {
//	        if (driver != null) {
//	            driver.quit();
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        driver = null; // always reset
//	    }
//	}
//}
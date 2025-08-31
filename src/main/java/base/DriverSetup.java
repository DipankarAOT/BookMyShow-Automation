//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import java.time.Duration;
//
//public class DriverSetup {
//    protected static WebDriver driver;
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//            String browser = ConfigLoader.get("browser");
//
//            switch (browser.toLowerCase()) {
//                case "chrome":
//                    driver = new ChromeDriver();
//                    break;
//                case "firefox":
//                    driver = new FirefoxDriver();
//                    break;
//                case "edge":
//                    driver = new EdgeDriver();
//                    break;
//                default:
//                    throw new RuntimeException("Unsupported browser: " + browser);
//            }
//
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
//                    Integer.parseInt(ConfigLoader.get("implicitWait"))));
//            driver.manage().window().maximize();
//            driver.get(ConfigLoader.get("url"));
//        }
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}

package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(); 
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // ✅ Always Chrome
            driver.manage().window().maximize();
        }
        return driver;
    }


//	
	public static void quitDriver() {
	    try {
	        if (driver != null) {
	            driver.quit();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        driver = null; // always reset
	    }
	}
	
//	public static void quitDriver() {
//	        if (driver != null) {
//	            driver.quit();
//	            driver=null;
//	        }
//	   
//	}
}
package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	
	WebDriver ldriver;
	
	JavascriptExecutor executor;
	WebDriverWait wait40;

 	
	 public LoginPage(WebDriver rdriver){
		 
		 ldriver = rdriver;
		 this.executor = (JavascriptExecutor) this.ldriver;
		 this.wait40 = new WebDriverWait(ldriver, Duration.ofSeconds(30));

		 PageFactory.initElements(rdriver, this);
	 }

	     //City Search
	 
		@FindBy (xpath="//input[@placeholder='Search for your city']")
		 
		 WebElement city;
		
		
	 //Sign-In Button On Home Page.
	 
	 @FindBy (xpath="//*[text()=\"Sign in\"]")
	 
	  WebElement SignInButton;
	 
	   
		
	  @FindBy (xpath=("//input[@id='userMobileNumber']"))
		 
	 WebElement MobileInput;
	  
	//@FindBy(xpath=("//*[@id=\"bottomSheet-model-close\"]/div/div/div[2]/div[2]/div[2]/div/div"))
	  @FindBy(xpath = "//*[@id='bottomSheet-model-close']/div/div/div[2]/div[2]/div[2]/div/div")
	  private WebElement continueButton;
	  
	  @FindBy(xpath="//div[@id='super-wrapper']//input[1]")
	  private WebElement enterOtp;
	  
	  @FindBy (xpath="//div[@class='sc-1ydq0aj-0 bIaakI']//*[name()='svg']")
	   WebElement backBtn;
	  
	  @FindBy(xpath="//*[@class=\"sc-1ydq0aj-6 gnsbYm\"]")
	  WebElement cBtn;
	  
	  
	  
	  public void citysearchClear() {
			
			city.clear();
			
	  }
		
		 public void SendCityName(String Pu ) {
			  
			  city.sendKeys(Pu);
		  }
		 
		 public void SendEnterKeys(Keys Enterkeys ) {
			  
			  city.sendKeys(Enterkeys);
		  }

		 public void clickCity() {
			 System.out.println("clicked");
			  
			  city.click();
		  }
	 
	 
	 public void SignIn() {
		 
		 SignInButton.click();
	 }
     

	  public void enterMobileNumber(String string) {
		  MobileInput.sendKeys(string);
	  }

	  public void clickContinue() {
		  continueButton.click();
		
	  }

	  public void enterOTP(String str) {
		enterOtp.sendKeys(str);
		
	  }

	  public void closePopup() {
		
		backBtn.click();
	  }
	  
	  public void closeSignin() {
		  cBtn.click();
	  }

	  
	  
	  


}
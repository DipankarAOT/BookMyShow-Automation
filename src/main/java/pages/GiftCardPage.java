package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftCardPage {
    private WebDriver driver;

    // Locators
    @FindBy(xpath = "//*[@id=\"super-container\"]/div[1]/div[2]/div/div/div[2]/div/a[4]\r\n")   // Gift Card navbar link
    private WebElement giftCardLink;

    @FindBy(xpath = "//div[text()='Check Gift Card Balance']") // Check Balance button/icon
    private WebElement checkBalance;

    @FindBy(xpath = "//input[@id='gift-voucher']")   // text box for voucher code
    private WebElement voucherInput;

    @FindBy(xpath = "//*[text()='Check Balance']")   // submit button
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@id='error-gift-voucher']") // error msg
    private WebElement errorMsg;

    @FindBy(xpath = "//input[@placeholder='Search for your city']")
    private WebElement city;

    // Constructor
    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void clickGiftCardIcon() {
        giftCardLink.click();
    }

    public void clickCheckBalanceIcon() {
        checkBalance.click();
    }

    public boolean isCheckBalanceIconDisplayed() {
        return checkBalance.isDisplayed();
    }

    public void enterVoucherCode(String code) {
        voucherInput.clear();
        voucherInput.sendKeys(code);
    }

    public void submitVoucher() {
        submitBtn.click();
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }

    public boolean isInvalidVoucherMessageDisplayed() {
        return errorMsg.isDisplayed();
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

    // Combined method (for invalid voucher test)
    public void checkInvalidVoucher(String code) {
        clickCheckBalanceIcon();
        enterVoucherCode(code);
        submitVoucher();
    }
}


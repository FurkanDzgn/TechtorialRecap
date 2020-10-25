package PageFactory;

import Utils.Config;
import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    public WebElement userName;

    @FindBy(name = "password")
    public WebElement passWord;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement loginButton;
}

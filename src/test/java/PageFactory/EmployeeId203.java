package PageFactory;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeId203 {

    public EmployeeId203(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[text()='203']")
    public WebElement employeeID203;

    @FindBy(xpath = "//td[text()='Susan']")
    public WebElement firstName;

    @FindBy(xpath = "//td[text()='Mavris']")
    public WebElement lastName;

    @FindBy(xpath = "//td[text()='Human Resources']")
    public WebElement humanResources;

    @FindBy(xpath = "//table//tbody//td")
    public List<WebElement> employeeInfo;
}

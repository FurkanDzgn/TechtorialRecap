package StepDefinitions;

import PageFactory.EmployeeId203;
import PageFactory.HomePage;
import PageFactory.LoginPage;
import Utils.Config;
import Utils.DataStorage;
import Utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.IOException;

public class UISteps {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    EmployeeId203 employeeId203 = new EmployeeId203();
    DataStorage dataStorage = DataStorage.getInstance();

    @When("user navigates to HrApp login page")
    public void user_navigates_to_HrApp_login_page() throws IOException {

        driver.get(Config.getProperty("uiconfigs","URL"));
    }

    @When("user logs in to application")
    public void user_logs_in_to_application() throws IOException {

        loginPage.userName.sendKeys(Config.getProperty("uiconfigs","username"));
        loginPage.passWord.sendKeys(Config.getProperty("uiconfigs","password"));
        loginPage.loginButton.click();
    }

    @When("user searchs for employeeId {string}")
    public void user_searchs_for_employeeId(String empId) throws InterruptedException {

        Thread.sleep(1000);
//        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
//        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.searchBox));
        homePage.searchBox.sendKeys(empId);
        homePage.searchButton.click();
    }

    @Then("user verifies employee info are matching with api response")
    public void user_verifies_employee_info_are_matching_with_api_response() throws InterruptedException {

//        String departmentName = dataStorage.getValue("getEmployee","department.departmentName").toString();
//        String salary = dataStorage.getValue("getEmployee","job.salary").toString();

        Thread.sleep(1000);
//        for(WebElement kk: employeeId203.employeeInfo){
//            System.out.println(kk.getText());
//        }

        Assert.assertEquals(dataStorage.getValue("getEmployee","firstName").toString(),
                employeeId203.firstName.getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","lastName").toString(),
                employeeId203.lastName.getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","employeeId").toString(),
                employeeId203.employeeID203.getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","department.departmentName").toString(),
                employeeId203.humanResources.getText());

        Driver.setClose();


    }

}

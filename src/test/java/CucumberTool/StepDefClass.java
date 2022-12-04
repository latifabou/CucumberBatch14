package CucumberTool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepDefClass {
    WebDriver driver;
    @Given("user is navigated to HMHs application")
    public void user_is_navigated_to_hm_hs_application() {
             WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("admin");
        WebElement passwordField=driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("Hum@nhrm123");
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement clickButton=driver.findElement(By.id("btnLogin"));
        clickButton.click();
    }
    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
       WebElement welcomeMessage=driver.findElement(By.id("welcome"));
       if(welcomeMessage.isDisplayed()){
           System.out.println("Test case is passed");
                    }else{
           System.out.println("Test case is failed");
       }
    }
}

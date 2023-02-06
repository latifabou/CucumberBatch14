package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class EmployeeListPage extends CommonMethods {
    @FindBy(id = "empsearch_id")
    public WebElement empSearchIdField;


    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement empSearchNameField;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    public List<WebElement> rowData;

    public EmployeeListPage() {
        PageFactory.initElements(driver, this);
    }
}

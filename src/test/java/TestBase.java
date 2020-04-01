import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String creds = "admin";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    protected void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(creds);
        driver.findElement(By.name("password")).sendKeys(creds);
        driver.findElement(By.name("login")).click();
    }

    protected void goToStore() {
        driver.get("http://localhost/litecart/");
    }

    protected boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator).isDisplayed());
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected boolean isSorted(List<WebElement> l) {
        for (int i = 1; i < l.size() - 1; i++) {
            if (l.get(i).getText().compareTo(l.get(i + 1).getText()) > 0
                    || l.get(i).getText().compareTo(l.get(i + 1).getText()) == 0 && l.get(i+1).getText()!=null) {
                System.out.println(l.get(i).getText() + " >" + l.get(i+1).getText()+ "<") ;

                return false;
            }
            System.out.println(l.get(i).getText()+" "+ l.get(i + 1).getText());
        }
        return true;
    }

    protected boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }


}

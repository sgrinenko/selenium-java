import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    private String creds = "admin";

    @Before
    public void setUp() throws MalformedURLException {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
        driver.quit();
        driver = null;
    }

    protected void loginAsAdmin() {
        driver.get("http://192.168.0.106/litecart/admin/");
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
                    || l.get(i).getText().compareTo(l.get(i + 1).getText()) == 0 && l.get(i + 1).getText() != null) {
                System.out.println(l.get(i).getText() + " >" + l.get(i + 1).getText() + "<");

                return false;
            }
            System.out.println(l.get(i).getText() + " " + l.get(i + 1).getText());
        }
        return true;
    }

    protected void checkBox(boolean set, By path) {
        WebElement el = driver.findElement(path);
        if (set && !el.isSelected()) {
            el.click();
        } else if (!set && el.isSelected()) {
            el.click();
        }
    }

    protected boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void setDatePicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(cssSelector))));
        ((JavascriptExecutor) driver).executeScript(String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screen = new File(".\\src\\test\\resources\\screen-"+ System.currentTimeMillis()+".png");
            try {
                Files.copy(tmp,screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }

        @Override
        public void beforeNavigateTo(String url, WebDriver driver) {
            System.out.println("Opening the URL :" + url);
        }
        @Override
        public void afterNavigateTo(String url, WebDriver driver) {
            System.out.println(url + " is opened ");
        }


    }

}

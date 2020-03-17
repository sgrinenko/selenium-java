
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest(){
        String search = "webdriver";
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys(search);
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
        wait.until(titleIs(search + " - Поиск в Google"));
    }

    @After
    public void afterTest(){
        driver.quit();
        driver = null;
    }
}

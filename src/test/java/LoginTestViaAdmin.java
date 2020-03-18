import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTestViaAdmin {
    private WebDriver driver;
    private WebDriverWait wait;
    private String creds ="admin";

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void testLoginAsAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(creds);
        driver.findElement(By.name("password")).sendKeys(creds);
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(
                driver.findElement(By.id("box-apps-menu-wrapper"))
                        .isDisplayed());
    }


    @After
    public void tearDown(){
        driver.quit();
        driver = null;
    }
}

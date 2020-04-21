package application;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Application {

    private CheckoutPage checkout;
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;

    public WebDriver driver;
    public WebDriverWait wait;

    public void quite() {
        driver.quit();
        driver = null;

    }

    public void init() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        productDetailsPage= new ProductDetailsPage(driver);
        checkout = new CheckoutPage(driver);
    }
    public void goToStore() {
        driver.get("http://localhost/litecart/");

    }

    public void goToHomePage(){
        if(!driver.getCurrentUrl().equals("http://localhost/litecart/en/")){
            driver.findElement(By.id("logotype-wrapper")).click();
            wait.until(ExpectedConditions.visibilityOfAllElements(homePage.isOnHomePage()));
        }

    }
    public HomePage homePage(){
        return homePage;
    }

    public ProductDetailsPage productDetailsPage(){
        return productDetailsPage;
    }

    public CheckoutPage checkout(){
        return checkout;
    }

    public void proceedToCheckout(){
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-summary")));
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

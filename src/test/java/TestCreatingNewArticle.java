import org.junit.Test;
        import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCreatingNewArticle extends TestBase {

    @Test
    public void createNewArticle() throws InterruptedException {
        loginAsAdmin();
        // ожидание не работает
        //wait.until(ExpectedConditions.titleIs("My Store"));
        // ожидание не работает
        //wait.until((WebDriver driver) -> driver.findElement(By.cssSelector("#chart-sales-monthly > canvas.jqplot-event-canvas")).isDisplayed());
        // ожидание Работает
        //Thread.sleep(1000);
        // ожидание не работает
        //isElementPresent((By.xpath("//li[@id='app-'][2]/a")));
        // ожидание не работает
        //wait.until((WebDriver driver) -> driver.findElement((By.xpath("//li[@id='app-'][2]/a"))).isEnabled());
        // ожидание не работает
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='app-'][2]/a")));
        // ожидание Работает
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='app-']")));
        driver.findElement(By.xpath("//li[@id='app-'][2]/a")).click();

        wait.until((WebDriver d)->driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
        driver.findElement(By.cssSelector("td#content a:nth-child(2)")).click();
        isElementPresent(By.xpath("//*[@id=]'content']//div[@class='tabs']"));
    }
}

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class TestNewWindowLinksOnCountriesPage extends TestBase {
    String currentWindow;
    @Test
    public void testLinksOnCountryPage() {
        loginAsAdmin();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='app-']")));
        driver.findElement(By.xpath("//li[@id='app-'][3]/a")).click();
        wait.until((WebDriver d) -> driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
        driver.findElement(By.cssSelector("i[class='fa fa-pencil']")).click();

        List<WebElement> links = driver.findElements(By.cssSelector("i.fa-external-link"));
        currentWindow = driver.getWindowHandle();
        for (WebElement link : links) {
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();
            driver.switchTo().window(getNewWindowFrom(allWindows));

            driver.close();
            driver.switchTo().window(currentWindow);
        }

    }
    private String getNewWindowFrom(Set<String> windows){
        windows.remove(currentWindow);
        return windows.iterator().next();
    }
}

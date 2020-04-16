import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.beans.binding.StringExpression;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckingThereIsNoMessagesInBroserLog extends TestBase{

    @Test
    public void testNoMessagesInBrowserLog(){
        loginAsAdmin();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='app-']")));
        driver.findElement(By.xpath("//li[@id='app-'][2]/a")).click();
        wait.until((WebDriver d) -> driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
        WebElement categoryFolder = driver.findElement(By.cssSelector(".dataTable tr.row i.fa-folder"));
        categoryFolder.findElement(By.xpath("./../a")).click();
        wait.until(ExpectedConditions.urlContains("?app=catalog&doc=catalog&category_id=1"));

        List<WebElement> categoriesProducts = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[3]/a[contains(@href,'category_id=1')]"));
        for (int i=1;i<categoriesProducts.size();i++){
           driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[3]/a[contains(@href,'category_id=1')]")).get(i).click();

            if(!driver.manage().logs().get("browser").getAll().isEmpty()) {
                driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
                throw  new  RuntimeException("while clicking on browser log contains message above");
            }
            driver.navigate().back();
        }
    }
}

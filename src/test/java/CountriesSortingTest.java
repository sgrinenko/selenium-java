import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CountriesSortingTest extends TestBase {

    @Test
    public void testCountriesSorting() {
        loginAsAdmin();
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//span[contains(.,'Countries')]")).click();
        isElementPresent(By.id("content"));

        WebElement countriesTable = driver.findElement(By.cssSelector(".dataTable"));
        List<WebElement> countiesNames = countriesTable.findElements(By.xpath(".//tr[@class='row']/td[5]/a"));
        Assert.assertTrue(isSorted(countiesNames));

        for (WebElement name :countiesNames){
            int i = countiesNames.indexOf(name);
            i+=1;
            String element = driver.findElement(By.xpath(".//table//tr[@class='row'][" +i +"]/td[6]")).getText();
            if (Integer.parseInt(element)>0){
                driver.findElement(By.xpath(".//table//tr[@class='row'][" +i +"]/td[5]/a")).click();
                isElementPresent(By.xpath("//h1[[contains(.,' Edit Country')]"));
                WebElement zonesTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zonesNames = zonesTable.findElements(By.xpath(".//tr/td[3]"));
                zonesNames.remove(zonesNames.size()-1);
                Assert.assertTrue(isSorted(zonesNames));
                driver.navigate().back();
                isElementPresent(By.id("content"));
            }

        }
    }


}
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingGeoZones extends TestBase {
    @Test
    public void testGeoZones() {
        loginAsAdmin();
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//span[contains(.,'Geo Zones')]")).click();
        isElementPresent(By.id("content"));

        WebElement geoZonesTable= driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> countriesNames = geoZonesTable.findElements(By.xpath(".//tr[@class='row']"));

        for (WebElement name : countriesNames){
            int i = countriesNames.indexOf(name);
            i+=1;
            driver.findElement(By.xpath("//table[@class='dataTable']//tr[@class='row']["+i+"]/td[3]/a")).click();
            isElementPresent(By.tagName("h2"));
            WebElement tableZones = driver.findElement(By.id("table-zones"));
            List<WebElement> zoneList = tableZones.findElements(By.xpath(".//td[3]/select"));
            isSorted(zoneList);
            driver.navigate().back();
        }

    }
}

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCreatingNewArticle extends TestBase {

    @Test
    public void createNewArticle() throws InterruptedException, FileNotFoundException {
        String productName = "Lambo";
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
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='app-']")));
        driver.findElement(By.xpath("//li[@id='app-'][2]/a")).click();

        wait.until((WebDriver d) -> driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
        List<WebElement> productList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        driver.findElement(By.cssSelector("td#content a:nth-child(2)")).click();
        isElementPresent(By.xpath("//*[@id=]'content']//div[@class='tabs']"));
        driver.findElement(By.cssSelector("div#tab-general input[name=status]:not([checked=checked])")).click();
        driver.findElement(By.xpath("//div[@id='tab-general']//tbody/tr[2]/td/span/input")).sendKeys(productName);
        driver.findElement(By.cssSelector("div#tab-general input[name=code]")).sendKeys(UUID.randomUUID() + "-Lambo");
        checkBox(true, By.xpath("//*[@id='tab-general']/table/tbody/tr[7]//table//tr[4]//input"));
        driver.findElement(By.cssSelector("div#tab-general input[name=quantity]")).sendKeys("1000");
        new Select(driver.findElement(By.cssSelector("div#tab-general select[name=delivery_status_id]"))).selectByIndex(1);
        new Select(driver.findElement(By.cssSelector("div#tab-general select[name=sold_out_status_id]"))).selectByValue("2");
        File file = new File("src\\test\\resources\\Lambo.jpg");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[9]//table//tr[1]/td/input")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("08-04-2020");
        driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("08-04-2021");
        new Actions(driver).moveByOffset(0, 50).click().perform();

        driver.findElement(By.xpath("//td[@id='content']//ul[@class='index']/li[2]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#tab-information select[name=manufacturer_id]"))));

        new Select(driver.findElement(By.cssSelector("#tab-information select[name=manufacturer_id]"))).selectByValue("1");
        driver.findElement(By.cssSelector("#tab-information input[name=keywords]")).sendKeys("Lamborgini");
        driver.findElement(By.xpath("//*[@id='tab-information']/table/tbody/tr[4]/td//input")).sendKeys("Lambo");
        driver.findElement(By.xpath("//*[@id='tab-information']/table/tbody/tr[5]/td//div[@class='trumbowyg-editor']")).sendKeys("Lamborgini");
        driver.findElement(By.xpath("//*[@id='tab-information']/table/tbody/tr[6]/td//input")).sendKeys("Lambo");
        driver.findElement(By.cssSelector("#tab-information input[name='meta_description[en]']")).sendKeys("Lambo");

        driver.findElement(By.xpath("//td[@id='content']//ul[@class='index']/li[4]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tab-prices")));

        WebElement purchasePrice = driver.findElement(By.xpath("//div[@id='tab-prices']/table[1]"));
        WebElement price = driver.findElement(By.xpath("//div[@id='tab-prices']/table[3]"));

        purchasePrice.findElement(By.name("purchase_price")).sendKeys("1000");
        new Select(purchasePrice.findElement(By.name("purchase_price_currency_code"))).selectByValue("USD");

        price.findElement(By.name("gross_prices[USD]")).sendKeys("1000");
        price.findElement(By.name("gross_prices[EUR]")).sendKeys("1000");

        driver.findElement(By.name("save")).click();
        wait.until((WebDriver d) -> driver.findElement(By.cssSelector("td#content h1")).getText().contains("Catalog"));

        List<WebElement> updatedProductList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        Assert.assertEquals(productList.size() + 1, updatedProductList.size());
        List<WebElement> foundNewProductNames = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']/td[3]"));
        for (WebElement name : foundNewProductNames) {
            if (name.getText().equals(productName)) {
                Assert.assertTrue(true);
                break;
            }else if (!name.getText().equals(productName) && foundNewProductNames.indexOf(name)==foundNewProductNames.size()){
                throw new RuntimeException("Not found expected product name");
            }
        }
    }
}

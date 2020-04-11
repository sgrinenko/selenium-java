import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckingAddAndDelItemsInCart extends TestBase {
    @Test
    public void checkAddingAndDeletingItemsInCart() {
        int expCartItems = 3;
        Integer countCart = 0;
        goToStore();
        for (int i = 1; i <= expCartItems; i++) {
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li[" + i + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".buy_now")));
            countCart = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            if (isElementPresent(By.cssSelector("div.buy_now select"))) {
                new Select(driver.findElement(By.cssSelector("div.buy_now select"))).selectByValue("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            Integer finalCountCurt = countCart;
            wait.until((WebDriver driver) -> Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()) == finalCountCurt + 1);
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='box-most-popular']")));
        }
        countCart++;
        Assert.assertEquals(expCartItems, Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()));
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-summary")));
        Assert.assertTrue(driver.findElements(By.cssSelector("#box-checkout-summary td.item")).size() == countCart);
        while (countCart > 0) {
            if (countCart != 1) {
                driver.findElement(By.cssSelector(".shortcut")).click();
            }
            driver.findElement(By.cssSelector("li.item button[name=remove_cart_item]")).click();
            Integer finalCountCart = countCart;
            wait.until((WebDriver driver) -> driver.findElements(By.cssSelector("#box-checkout-summary td.item")).size() < finalCountCart);
            countCart--;
        }
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#checkout-cart-wrapper em")), "no items in your cart"));
        driver.findElement(By.cssSelector("#checkout-cart-wrapper a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-most-popular")));
    }
}

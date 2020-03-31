import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingStickers extends TestBase {
    @Test
    public void checkStickers() {
        goToStore();
        List<WebElement> products = driver.findElements(By.cssSelector(".product"));
        for (WebElement product : products) {
           List<WebElement> stickerInProduct = product.findElements(By.cssSelector("div.sticker"));
           Assert.assertTrue(stickerInProduct.size()==1);
        }

    }
}

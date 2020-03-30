import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingStickers extends TestBase {
    @Test
    public void checkStickers() {
        goToStore();
        List<WebElement> stickers = driver.findElements(By.cssSelector(".product div.sticker"));
        int newSticker = 0;
        int saleSticker = 0;
        for (WebElement sticker : stickers) {
            if (sticker.getText().equals("NEW")) {
                newSticker++;
            } else if (sticker.getText().equals("SALE")) {
                saleSticker++;
            }
        }
        Assert.assertEquals(stickers.size(), saleSticker + newSticker);
    }
}

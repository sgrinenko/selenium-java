import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingStrickers extends TestBase {

    @Test
    public void checkStickers() {
        goToStore();
        List<WebElement> stickers = driver.findElements(By.cssSelector("a.link div.image-wrapper > div.sticker"));
        for (WebElement sticker : stickers) {
            if (!sticker.getText().equals("NEW") && !sticker.getText().equals("SALE")) {
                throw new RuntimeException(sticker.getText());
            }
        }
    }
}

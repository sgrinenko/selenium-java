import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import sun.awt.image.PixelConverter;
import sun.plugin.dom.css.RGBColor;

public class CheckingArticleDetails extends TestBase {
    @Test
    public void checkArticle() {
        goToStore();
        WebElement article = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement articleRegularPrice = article.findElement(By.cssSelector("div.price-wrapper > .regular-price"));
        WebElement articleCampaignPrice = article.findElement(By.cssSelector("div.price-wrapper > .campaign-price"));
        String articleName = article.findElement(By.cssSelector("div.name")).getText();

        String articleRegularPriceText = articleRegularPrice.getText();
        String articleRegularPriceColor = articleRegularPrice.getCssValue("color");
        String articleRegularPriceFont = articleRegularPrice.getCssValue("text-decoration");
        Dimension articleRegularPriceSize = articleRegularPrice.getSize();

        String articleCampaignPriceText = articleCampaignPrice.getText();
        String articleCampaignPriceColor = articleCampaignPrice.getCssValue("color");
        String articleCampaignPriseFont = articleCampaignPrice.getCssValue("font-weight");
        Dimension articleCampaignPriseSize = articleCampaignPrice.getSize();

        Assert.assertTrue(isCrossed(articleRegularPriceFont) && isGrey(articleRegularPriceColor));
        Assert.assertTrue(isBold(articleCampaignPriseFont) && isRed(articleCampaignPriceColor));
        Assert.assertTrue(articleCampaignPriseSize.height > articleRegularPriceSize.height && articleCampaignPriseSize.width > articleRegularPriceSize.width);

        article.click();
        isElementPresent(By.id("box-product"));

        String articleDetailsName = driver.findElement(By.cssSelector("h1.title[itemprop=name]")).getText();
        WebElement articleDetailsRegularPrise = driver.findElement(By.cssSelector("div.price-wrapper > .regular-price"));
        WebElement articleDetailsCampaignPrise = driver.findElement(By.cssSelector("div.price-wrapper > .campaign-price"));

        String articleDetailsRegularPriseText = articleDetailsRegularPrise.getText();
        String articleDetailsRegularPriceColor = articleDetailsRegularPrise.getCssValue("color");
        String articleDetailsRegularPriceFont = articleDetailsRegularPrise.getCssValue("text-decoration");
        Dimension articleDetailsRegularPriceSize = articleDetailsRegularPrise.getSize();

        String articleDetailsCampaignPriseText = articleDetailsCampaignPrise.getText();
        String articleDetailsCampaignPriceColor = articleDetailsCampaignPrise.getCssValue("color");
        String articleDetailsCampaignPriseFont = articleDetailsCampaignPrise.getCssValue("font-weight");
        Dimension articleDetailsCampaignPriseSize = articleDetailsCampaignPrise.getSize();

        Assert.assertTrue(isCrossed(articleDetailsRegularPriceFont) && isGrey(articleDetailsRegularPriceColor));
        Assert.assertTrue(isBold(articleDetailsCampaignPriseFont) && isRed(articleDetailsCampaignPriceColor));
        Assert.assertTrue(articleDetailsCampaignPriseSize.height > articleDetailsRegularPriceSize.height && articleDetailsCampaignPriseSize.width > articleDetailsRegularPriceSize.width);

        Assert.assertEquals(articleName,articleDetailsName);
        Assert.assertEquals(articleRegularPriceText,articleDetailsRegularPriseText);
        Assert.assertEquals(articleCampaignPriceText,articleDetailsCampaignPriseText);


    }

    private boolean isBold(String s) {
        boolean state=true;
        switch (s) {
            case "100" :
            case "200" :
            case "300" :
            case "400" :
            case "500" :
            case "600" :
                state = false;
                break;
            case "700" :
            case "800" :
            case "900" :
                state = true;
                break;
        }
        return state;
    }
    private boolean isCrossed(String s){
        return s.contains("line-through");
    }

    private boolean isGrey(String s){
        Integer[] rgb = getRGBValues(s);
        return rgb[0].equals(rgb[1]) && rgb[0].equals(rgb[2]);
    }
    private boolean isRed(String s){
        Integer[] rgb = getRGBValues(s);
        return rgb[1].equals(0) && rgb[2].equals(0);
    }

    private Integer[] getRGBValues(String s){
        //rgba(119, 119, 119, 1) chrome
        //rgb(119, 119, 119) ff
        //rgba(119, 119, 119, 1) ie
        //rgb(119, 119, 119) edge
        s= s.substring(s.indexOf("(")+1,s.indexOf(")"));
        String[] split = s.split(",");
        return new Integer[]{Integer.parseInt(split[0].trim()),Integer.parseInt(split[1].trim()),Integer.parseInt(split[2].trim())};
    }
}

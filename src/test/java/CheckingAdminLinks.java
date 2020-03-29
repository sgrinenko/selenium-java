import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingAdminLinks extends TestBase {


    @Test
    public void checkLinks() {
        loginAsAdmin();
        List<WebElement> links = driver.findElements(By.id("app-"));
        for (int i = 1; i <= links.size(); i++) {
            WebElement link = driver.findElement(By.xpath("//li[@id='app-'][" + i + "]"));
            link.click();
            isElementPresent(By.cssSelector("#app-.selected"));
            if (isElementPresent(By.cssSelector(".docs"))) {
                checkSubLinks();
            } else {
                String linkText = driver.findElement(By.cssSelector("#app-.selected")).getText().trim();
                String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                Assert.assertEquals(linkText, contentText);
            }
        }
    }

    private void checkSubLinks() {
        List<WebElement> subLinks = driver.findElements(By.cssSelector(".docs li"));
        for (int i = 1; i <= subLinks.size(); i++) {
            String linkText = driver.findElement(By.cssSelector("#app-.selected span.name")).getText();
            if (linkText.equals("Modules")) {
                String subLinkText = driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).getText();
                if (!subLinkText.equals("Background Jobs")) {
                    driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                    String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                    Assert.assertEquals(subLinkText.concat(" ").concat(linkText), contentText);
                } else {
                    driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                    String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                    Assert.assertEquals("Job Modules", contentText);
                }

            } else if (linkText.equals("Settings")) {
                driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                Assert.assertEquals(linkText, contentText);
            } else if (linkText.equals("Translations")) {
                String subLinkText = driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).getText();
                if (!subLinkText.equals("Scan Files")) {
                    driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                    String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                    Assert.assertEquals(subLinkText, contentText);
                } else {
                    driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                    String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                    Assert.assertEquals(subLinkText.concat(" For Translations"), contentText);
                }
            } else {
                String subLinkText = driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).getText();
                driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
                String contentText = driver.findElement(By.cssSelector("td#content > h1")).getText().trim();
                Assert.assertEquals(subLinkText, contentText);
            }
        }
    }

}

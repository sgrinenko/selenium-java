import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckUserRegistration extends TestBase {
        String rndStr = UUID.randomUUID().toString();


    @Test
    public void userRegistrationTest() {
        Random rnd = new Random(System.currentTimeMillis());
        goToStore();
        driver.findElement(By.cssSelector("div.content table a")).click();
        isElementPresent(By.cssSelector("h1[title*=Create Account]"));
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("testUserName");
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("testUserLastName");
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(String.format("someWhere street %s buildind %s", new AtomicInteger().incrementAndGet(), new AtomicInteger().incrementAndGet()));
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("BigCity");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("380111111111");
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys( rndStr +"@gmail.com");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(""+rndStr);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(""+rndStr);
        checkBox(false,By.name("newsletter"));
        Select ddCountry = new Select(driver.findElement(By.name("country_code")));
        ddCountry.selectByValue("US");
        isElementPresent(By.cssSelector("span.select2-selection__rendered[title*=United States]"));
        Select ddRegion = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        ddRegion.selectByVisibleText("Montana");
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("9470" + rnd.nextInt(9));

        driver.findElement(By.name("create_account")).click();
        isElementPresent(By.cssSelector("notices > .notice success"));

        driver.findElement(By.cssSelector("#box-account ul > li:nth-child(4) > a")).click();
        isElementPresent(By.cssSelector("#box-account-login > h3"));
        driver.findElement(By.name("email")).sendKeys(""+ rndStr +"@gmail.com");
        driver.findElement(By.name("password")).sendKeys(""+rndStr);
        driver.findElement(By.name("login")).click();
        isElementPresent(By.cssSelector("notices > .notice success"));

    }


}

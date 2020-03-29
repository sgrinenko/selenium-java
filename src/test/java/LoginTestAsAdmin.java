
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTestAsAdmin extends TestBase {
    @Test
    public void testLoginAsAdmin(){
        loginAsAdmin();
        Assert.assertTrue(
                driver.findElement(By.id("box-apps-menu-wrapper"))
                        .isDisplayed());
    }
}

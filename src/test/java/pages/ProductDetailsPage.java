package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailsPage extends Page{

    @FindBy(name = "add_cart_product")
    WebElement addToCart;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void isOnProductDetails(){
        if (!addToCart.isDisplayed()){
            wait.until(ExpectedConditions.visibilityOf(addToCart));
        }
    }


    public void addProductToCart() {
        int cartBefore = getCartQuantity();
        if (isElementPresent(By.cssSelector("div.buy_now select"))) {
            new Select(driver.findElement(By.cssSelector("div.buy_now select"))).selectByValue("Small");
        }
        addToCart.click();
        wait.until((WebDriver driver) -> getCartQuantity() == cartBefore + 1);
    }



}

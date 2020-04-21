package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends Page {

    @FindBy(css="#box-checkout-summary td.item")
    List<WebElement> orderSummary;
    @FindBy(css="#checkout-cart-wrapper em")
    WebElement emptyCart;
    @FindBy(css=".shortcuts li.shortcut")
    WebElement productsShortCart;
    @FindBy(name = "remove_cart_item")
    WebElement removeButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


    public int getOrderSummary() {
        return orderSummary.size();
    }

    public void checkNoProductsLeft() {
        wait.until(ExpectedConditions.textToBePresentInElement(emptyCart, "no items in your cart"));
    }

    public void removeFromCart() {
        if (driver.findElements(By.cssSelector("#box-checkout-summary td.item")).size()!=1){
            productsShortCart.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
    }
}

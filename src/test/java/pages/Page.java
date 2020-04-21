package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = "span.quantity")
    WebElement cartQuantity;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }



    public int getCartQuantity(){
        return Integer.parseInt(cartQuantity.getText());
    }


    protected boolean isElementPresent(By locator) {
        try {
            wait = new WebDriverWait(driver,3);
            wait.until((WebDriver d) -> d.findElement(locator).isDisplayed());
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }


    protected boolean isSorted(List<WebElement> l) {
        for (int i = 1; i < l.size() - 1; i++) {
            if (l.get(i).getText().compareTo(l.get(i + 1).getText()) > 0
                    || l.get(i).getText().compareTo(l.get(i + 1).getText()) == 0 && l.get(i + 1).getText() != null) {
                System.out.println(l.get(i).getText() + " >" + l.get(i + 1).getText() + "<");

                return false;
            }
            System.out.println(l.get(i).getText() + " " + l.get(i + 1).getText());
        }
        return true;
    }

    protected void checkBox(boolean set, By path) {
        WebElement el = driver.findElement(path);
        if (set && !el.isSelected()) {
            el.click();
        } else if (!set && el.isSelected()) {
            el.click();
        }
    }

    protected boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void setDatePicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(cssSelector))));
        ((JavascriptExecutor) driver).executeScript(String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }
}

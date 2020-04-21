package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends Page {

    @FindBy(xpath = "//div[@id='box-most-popular']//li")
    public List<WebElement> mostPopularArticles;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void pickMostPopularArticle(int i) {
            mostPopularArticles.get(i).click();
    }

    public int countMostPopularArticles(){
        return mostPopularArticles.size();
    }


    public List<WebElement> isOnHomePage() {
        return mostPopularArticles;
    }
}

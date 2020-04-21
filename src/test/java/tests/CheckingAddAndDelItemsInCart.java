package tests;

import org.junit.Test;


public class CheckingAddAndDelItemsInCart extends TestBase {
    @Test
    public void checkAddingAndDeletingItemsInCart() {
        app.goToHomePage();
        for (int i= 0; i< app.homePage().countMostPopularArticles();i++){
            app.homePage().pickMostPopularArticle(i);
            app.productDetailsPage().isOnProductDetails();
            app.productDetailsPage().addProductToCart();
            app.goToHomePage();
        }
        app.proceedToCheckout();

        for (int i =0 ;i <= app.checkout().getOrderSummary();i++){
            app.checkout().removeFromCart();
        }
        app.checkout().checkNoProductsLeft();
        app.goToHomePage();

    }
}

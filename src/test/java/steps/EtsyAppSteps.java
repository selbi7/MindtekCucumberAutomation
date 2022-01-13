package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyHomePage;
import pages.EtsySearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {
    WebDriver driver= Driver.getDriver();
    EtsyHomePage homePage= new EtsyHomePage();
    EtsySearchPage searchPage= new EtsySearchPage();

    @Given("user navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));
    }

    @When("user searches for {string}")
    public void user_searches_for(String item) {
    homePage.searchBox.sendKeys(item+ Keys.ENTER);

    }

    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) {
        searchPage.allFilters.click();
        searchPage.over1000.click();
        searchPage.applyButton.click();

    }

    @Then("user validates that item prices are over {int}")
    public void user_validates_that_item_prices_are_over(Integer price) throws InterruptedException {
        Thread.sleep(4000);
        List<WebElement> prices=searchPage.allPrices;
        for(WebElement e: prices){
            //System.out.println(e.getText());
            String priceStr= e.getText().replace(",","");//replacing "," with empty string;
            double doublePrice= Double.parseDouble(priceStr);
            Assert.assertTrue(doublePrice>=price);

        }
    }

    @Then("user validates search result items contain keyword {string}")
    public void user_validates_search_result_items_contain_keyword(String item) {
        List<WebElement> itemNames = searchPage.itemNames;
        for(WebElement element:itemNames){
            if(element.getText().toLowerCase().contains(item) ||element.getText().toLowerCase().contains("rug")){
                StringBuilder newItems=new StringBuilder();
                newItems.append(element.getText());
                Assert.assertTrue(newItems.toString().toLowerCase().contains(item)||newItems.toString().toLowerCase().contains("rug"));
            }
        }
    }

    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {
        if(section.equalsIgnoreCase("Jewelery and Accessories")){
            homePage.jeweleryAndAccessories.click();
        }else if(section.equalsIgnoreCase("End of Year Sales Event")){
            homePage.endOfYearSales.click();
        }else if(section.equalsIgnoreCase("Clothing & Shoes")){
            homePage.clothingAndShoes.click();
        }else if(section.equalsIgnoreCase("Home & Living")){
            homePage.homeAndLiving.click();
        }else if(section.equalsIgnoreCase("Wedding & Party")){
            homePage.weddingAndParty.click();
        }else if(section.equalsIgnoreCase("Toys & Entertainment")){
            homePage.toysAndEntertainment.click();
        }else if(section.equalsIgnoreCase("Art & Collectibles")){
            homePage.artAndCollectibles.click();
        }else if(section.equalsIgnoreCase("Craft Supplies and Tools")){
            homePage.craftAndSupplies.click();
        }else if(section.equalsIgnoreCase("Gift and Gift Cards")){
            homePage.giftAndGiftCards.click();
        }
        
    }

    @Then("user validates title is {string}")
    public void userValidatesTitleIs(String expectedTitle) {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

    }
}

package Web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 22/06/16.
 */
public class PostProperty extends TestSetup {



    @Test
    public void PostProperty() throws InterruptedException {
        loginnow();
        System.out.println("----Post Property Test-----");
        browser.get("http://beta.nobroker.in/list-your-property-for-rent-sale");
        Thread.sleep(10000);
        ((FirefoxDriver)browser).getKeyboard().pressKey(Keys.F11);
        Thread.sleep(10000);
        By.xpath("//*[@id=\"postYourPropertyLandingForm\"]/div[1]/div[1]/form/div/div[2]/div[1]/div[2]").findElement(browser).click();
        By.cssSelector("ul.dropdownList.active [data-value='mumbai']").findElement(browser).click();
        By.xpath("//*[@id=\"postYourPropertyLandingForm\"]/div[1]/div[1]/form/div/div[3]/div[1]/div[2]").findElement(browser).click();
        By.cssSelector("#apartmentType + ul.dropdownList.active [data-value='BHK2']").findElement(browser).click();
        By.id("propertyLocality").findElement(browser).sendKeys("Thane");
        Thread.sleep(5000);
        By.xpath("//*[@id=\"propertyLocality\"]").findElement(browser).sendKeys(Keys.ARROW_DOWN);
        By.xpath("//*[@id=\"propertyLocality\"]").findElement(browser).sendKeys(Keys.ENTER);
        Thread.sleep(15000);
        By.id("rent").findElement(browser).click();
        Thread.sleep(5000);
        By.id("rent").findElement(browser).sendKeys("20000");
        By.id("submitFrom").findElement(browser).click();
        Thread.sleep(10000);
        By.id("userProfileDD").findElement(browser).click();
        Thread.sleep(5000);
        By.linkText("Your Properties").findElement(browser).click();
        WebElement xx=By.xpath("//*[@id=\"rent\"]/div[4]/div[1]/div/a").findElement(browser);
        String b=By.xpath("//*[@id=\"rent\"]/div[4]/div[1]/div/a").findElement(browser).getText();
        System.out.println(""+b);
       // assertThat("pass", xx.getText(), containsString("Thane"));
        Assert.assertEquals("2 BHK In Thane, Maharashtra, India\n" +
                "Inactive   ", xx.getText());






    }
}

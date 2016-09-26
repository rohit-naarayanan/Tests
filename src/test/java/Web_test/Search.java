package Web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 23/06/16.
 */
public class Search extends TestSetup
{
    String searcharea="Whitefield";
    @Test
    public void Search () throws InterruptedException {

        System.out.println("!----------Search test-----------! ");

        browser.get(baseurl);
        By.id("nbCitySelector").findElement(browser).click();
        By.cssSelector("ul.cityselector a[data-key='bangalore']").findElement(browser).click();
        By.id("nbBHKSelector").findElement(browser).click();
        By.cssSelector("ul.dropdown [value='1']").findElement(browser).click();
        By.id("locationGoogle").findElement(browser).sendKeys(searcharea);
        Thread.sleep(5000);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ARROW_DOWN);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ENTER);

        Thread.sleep(5000);
        By.id("searchButtonHomePage").findElement(browser).click();
        Thread.sleep(5000);
        WebElement loc= browser.findElement(By.xpath("//*[@id=\"loc\"]"));
        String area =loc.getText();
        System.out.println("" +area);
        //System.out.println(""+searcharea);
        Assert.assertEquals(""+searcharea, loc.getText());
    }
}


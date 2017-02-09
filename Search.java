package in.nobroker.automation.desktop.Webtest.firefox;

/**
 * Created by RohitNaarayanan on 23/06/16.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by RohitNaarayanan on 23/06/16.
 */
public class Search extends TestSetup
{
    public static WebDriver browser1=new FirefoxDriver();
    String searcharea="Whitefield";
    @Test
    public void Search () throws InterruptedException {

        System.out.println("!----------Search test-----------! ");

        browser1.get(baseurl);
        Thread.sleep(10000);
        By.id("nbCitySelector").findElement(browser1).click();
        By.cssSelector("ul.cityselector a[data-key='bangalore']").findElement(browser1).click();
        By.id("nbBHKSelector").findElement(browser1).click();
        By.cssSelector("ul.dropdown [value='1']").findElement(browser1).click();
        By.id("locationGoogle").findElement(browser1).sendKeys(searcharea);
        Thread.sleep(5000);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser1).sendKeys(Keys.ARROW_DOWN);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser1).sendKeys(Keys.ENTER);

        Thread.sleep(5000);
        By.id("searchButtonHomePage").findElement(browser1).click();
        Thread.sleep(5000);
        WebElement loc= browser1.findElement(By.xpath("//*[@id=\"loc\"]"));
        String area =loc.getText();
        System.out.println("" +area);
        Assert.assertEquals(""+searcharea, loc.getText());
        browser1.close();
    }

}



package Web_test;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Created by RohitNaarayanan on 16/06/16.
 */
public class ShortlistTest extends TestSetup{

    //public static WebDriver browser;
    //WebDriver driver;
    //public WebDriver browser = new FirefoxDriver();

    @Test
    public void shortlist() throws InterruptedException

    {
        /*calling Log in function from Test setup */
        loginnow();


        Thread.sleep(20000);
        By.id("nbCitySelector").findElement(browser).click();
        By.cssSelector("ul.cityselector a[data-key='bangalore']").findElement(browser).click();
        By.id("nbBHKSelector").findElement(browser).click();
        By.cssSelector("ul.dropdown [value='1']").findElement(browser).click();
        By.id("locationGoogle").findElement(browser).sendKeys("Whitefield");
        Thread.sleep(5000);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ARROW_DOWN);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        By.id("searchButtonHomePage").findElement(browser).click();
        //browser.get(Shortlistarea);
        Thread.sleep(5000);
        By.xpath("//*[@id=\"listResults\"]/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/button[1]").findElement(browser).click();
        WebElement element = browser.findElement(By.xpath("//*[@id=\"listResults\"]/div[3]/div[1]/div/a"));
        Thread.sleep(5000);
        System.out.println("ID of property : " + element.getAttribute("href"));
        String id1=element.getAttribute("href");
        Thread.sleep(5000);
        By.id("userProfileDD").findElement(browser).click();
        Thread.sleep(5500);
        By.linkText("Shortlists").findElement(browser).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"content\"]/div[18]/div[2]/div[2]/div/div[1]/div[2]/div/div").findElement(browser).click();
        Thread.sleep(5000);
        String a = By.xpath("//*[@id=\"user_summary_detail_container\"]/div[1]/div/div[2]/div[2]/div[2]").findElement(browser).getText();
        System.out.println("Area Shortlisted : "+a);

        /*Checking if the id of property matches from listing page to shortlist page*/

        WebElement id = browser.findElement(By.xpath("//*[@id=\"user_summary_detail_container\"]/div[1]/div/div[1]/a"));
        String id2=id.getAttribute("href");
        Assert.assertEquals(id1,id2);
        browser.close();
//        String[] splited = id2.split("\\b+");
//        System.out.println(" Test status :"+ Arrays.asList(splited).contains(id1));
//        if(Arrays.asList(splited).contains(id1))
//        {
//            System.out.println("Test passed");
//            browser.close();
//        }
//        //System.out.println("Test failed");
//
//        browser.close();
    }

}

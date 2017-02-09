package in.nobroker.automation.desktop.Webtest.firefox;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * Created by RohitNaarayanan on 16/06/16.
 */
public class ShortlistTest extends TestSetup{

    //public static WebDriver browser;
    //WebDriver driver;
    public WebDriver browser3 = new FirefoxDriver();

    @Test
    public void shortlist() throws InterruptedException

    {
        /*calling Log in function from Test setup */
        browser = new FirefoxDriver();
        System.out.println("!----------Sign up test-----------! ");
        System.out.println(end);
        browser.get(baseurl);
        browser.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        browser.findElement(By.linkText("Sign up")).click();
        By.xpath("//*[@id=\"newAccountName\"]").findElement(browser).sendKeys(username);
        By.xpath("//*[@id=\"inputEmail\"]/input").findElement(browser).sendKeys(email + "@gmail.com");
        By.xpath("//*[@id=\"user_password\"]").findElement(browser).sendKeys("123456");
        end1 = email.substring(0, Math.min(email.length(), 10));
        By.xpath("//*[@id=\"newAccountPhone\"]").findElement(browser).sendKeys(end1);
        // browser.findElement(By.)
        Thread.sleep(5000);
        By.xpath("//*[@id=\"registerUser\"]/div/a").findElement(browser).click();
        System.out.println("===Short List Test===");
        Thread.sleep(5000);
        browser.get(baseurl + "/property/rent/bangalore/Whitefield?type=BHK2&nbPlace=ChIJg_wNXfMRrjsR-RUB2BKlzzA&lat_lng=12.9697999,77.74994670000001&radius=1.0&sharedAccomodation=0");
        browser.findElement(By.xpath("(//button[@type='button'])[35]")).click();
        WebElement propid=browser.findElement(By.xpath("//*[@id=\"contactOwnerBtnff8081815731a419015731b38bfb02f3\"]"));
        String idfull =propid.getAttribute("id");
        System.out.println(idfull);
        String id =idfull.substring(10);
        System.out.println(id);
       browser.get("https://beta.nobroker.in/dashboard");
        browser.findElement(By.cssSelector("div.user_summary_header.us_header_shortlisted > div.summary_count")).click();

//
        WebElement element = browser.findElement(By.cssSelector("driver.findElement(By.cssSelector(\"div.user_summary_header.us_header_shortlisted > div.summary_count"));
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

        WebElement id3 = browser.findElement(By.xpath("//*[@id=\"user_summary_detail_container\"]/div[1]/div/div[1]/a"));
        String id2=id3.getAttribute("href");
        Assert.assertEquals(id1,id2);
       // browser.close();
//        String[] splited = id2.split("\\b+");
//        System.out.println(" Test status :"+ Arrays.asList(splited).contains(id1));
//        if(Arrays.asList(splited).contains(id1))
//        {
//            System.out.println("Test passed");
//            browser3.close();
//        }
//        //System.out.println("Test failed");
//
//        browser3.close();
    }

}

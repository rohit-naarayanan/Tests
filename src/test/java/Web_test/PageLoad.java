package Web_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 03/08/16.
 */
public class PageLoad extends TestSetup {
    @Test
    public void PageLoad()throws InterruptedException {
        browser.get(baseurl);
        By.id("userProfileDD").findElement(browser).click();
        browser.findElement(By.linkText("Sign Out")).click();
        long startTime1 = System.currentTimeMillis() / 1000;
        //System.out.println("----LOGGING IN----");

        Thread.sleep(5000);
        By.linkText("Log in").findElement(browser).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"userName\"]").findElement(browser).sendKeys(un);

        /**Using CSS selector since id for paswword field is same**/

        By.cssSelector("#dosignin #user_password").findElement(browser).sendKeys(pwd);
//        By.xpath("//*[@id=\"user_password\"]").findElement(browser).sendKeys("rohit.naarayanan92@gmail.com");
        Thread.sleep(5000);
        By.id("signInButton").findElement(browser).click();
        Thread.sleep(10000);
        long endTime1 = System.currentTimeMillis() / 1000;
        long loadTime1 = endTime1 - startTime1-15;
        System.out.println("\nTotal login time: " + loadTime1 + " seconds");
         browser.get("http://beta.nobroker.in/tenant/plans");
        Thread.sleep(10000);
        long startTime = System.currentTimeMillis() / 1000;
        //browser.findElement(By.cssSelector("div.overlay.jx_ui_Widget")).click();
        browser.findElement(By.xpath("(//button[@id='freePlanButton'])[2]")).click();
        //Thread.sleep();

       // WebElement search = browser.findElement(By.linkText("Log in"));
        //Iterate through the loop as long as time(60sec) is with in the acceptable Page load time
        long endTime = System.currentTimeMillis() / 1000;
        //System.out.println("The endTime is " + endTime);
        long loadTime = endTime - startTime;
        System.out.println("\nPayment Load time: " + loadTime + " seconds\n");



    }     }






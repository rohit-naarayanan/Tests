package Web_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 29/06/16.
 */
public class Payment extends TestSetup {

@Test
public void Payment() throws InterruptedException {
    loginnow();
        browser.get("http://beta.nobroker.in/tenant/plans");
    Thread.sleep(5000);
    By.id("relaxPlanButtonCost").findElement(browser).click();
    Thread.sleep(10000);
    //Alert alert=browser.switchTo().alert();
    //alert.getClass();
    By.cssSelector("div.payment-option:nth-child(1)").findElement(browser).click();
   //By.cssSelector("#payment-options .item.payment-option[tab='card']").findElement(browser).click();
   // By.cssSelector("id[class='payment-option item'][value='card']").findElement(browser).click();


    }
}

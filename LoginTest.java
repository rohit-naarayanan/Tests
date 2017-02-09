package in.nobroker.automation.desktop.Webtest.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 20/06/16.
 */
public class LoginTest extends TestSetup {
    //public static WebDriver browser;

    //public WebDriver driver;
  public WebDriver browser2 = new FirefoxDriver();


    @Test
    public void login() throws InterruptedException {
        //browser=new FirefoxDriver();
        System.out.println("----LOGIN TEST----");
        browser2.get(baseurl);
        Thread.sleep(5000);
        By.linkText("Log in").findElement(browser2).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"userName\"]").findElement(browser2).sendKeys(un1);

        /**Using CSS selector since id for paswword field is same**/

        By.cssSelector("#dosignin #user_password").findElement(browser2).sendKeys(pwd1);
        Thread.sleep(10000);
        //browser2.findElement(By.id("signInButton")).click();
        By.id("signInButton").findElement(browser2).click();
        Thread.sleep(10000);


        if (browser2.findElements(By.id("userProfileDD")).isEmpty())
        {
              browser2.close();
            //throw new IllegalStateException("Login error");
            System.out.println("Login fail");
        } else
        {
            System.out.println("Login succesfull");

            browser2.close();
        }


    }

}
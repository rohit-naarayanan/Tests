package Web_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 15/06/16.
 */
public class LoginTest extends TestSetup {
//public static WebDriver browser;

   //public WebDriver driver;
//   public WebDriver browser = new FirefoxDriver();


    @Test
    public void login() throws InterruptedException {
        //browser=new FirefoxDriver();
        System.out.println("----LOGIN TEST----");
        browser.get(baseurl);
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

        if (browser.findElements(By.id("userProfileDD")).isEmpty())
        {
            browser.close();
            throw new IllegalStateException("Login error");

        } else
        {
            System.out.println("Login succesfull");

            browser.close();
        }


        }


    }







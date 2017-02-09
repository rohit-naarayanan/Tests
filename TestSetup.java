package in.nobroker.automation.desktop.Webtest.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by gitRohitNaarayanan on 20/06/16.
 */
public class TestSetup {

    public static WebDriver d;
    public static WebDriver browser=new FirefoxDriver();

    String un = "rohit.naarayanan92@gmail.com";
    String pwd = "Password@123";
    String un1 = "postproperty@gmail.com";
    String pwd1 = "123456";
    String baseurl = "http://beta.nobroker.in/";
    String username = "New user";// New Account name used for registration in Signup test
    String Shortlistarea = "http://beta.nobroker.in/property/sale/bangalore/Sanjaynagar?nbPlace=ChIJ85O6tekXrjsR13jdBn4Ge-c&lat_lng=13.0364641,77.5767631&radius=1.0/";//Paste the area link where you need to shortlist
    String url;
    Long end = System.currentTimeMillis();
    String email= Long.toString(end);
    String end1;
    public void geturl() {
        d = new FirefoxDriver();
        d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        d.get(baseurl);
    }


    public void loginnow() throws InterruptedException {

        browser = new FirefoxDriver();
        System.out.println("----LOGGING IN----");
        browser.get(baseurl);
        Thread.sleep(5000);
        By.linkText("Log in").findElement(browser).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"userName\"]").findElement(browser).sendKeys(un1);

        /**Using CSS selector since id for paswword field is same**/

        By.cssSelector("#dosignin #user_password").findElement(browser).sendKeys(pwd1);
//        By.xpath("//*[@id=\"user_password\"]").findElement(browser).sendKeys("rohit.naarayanan92@gmail.com");
        Thread.sleep(5000);
        By.id("signInButton").findElement(browser).click();
        Thread.sleep(10000);
        url=browser.getCurrentUrl();


    }
    public void signup() throws InterruptedException

    {
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
        By.xpath("//*[@id=\"registerUser\"]/div/a").findElement(browser).click();
    }

}
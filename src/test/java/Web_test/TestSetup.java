package Web_test;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by RohitNaarayanan on 18/06/16.
 */
public class TestSetup {

    public static WebDriver d;
    public static WebDriver browser=new FirefoxDriver();
    //public static WebDriver chrome=new ChromeDriver();


    String un = "paymenttest@gmail.com";
    String pwd = "123456";
    String baseurl = "http://beta.nobroker.in/";
    String username = "New user";// New Account name used for registration in Signup test
    String newaccemail = "rohit.naa@gmail.com";// New Account email used for registration in Signup test
    String newpwd = "Password123";// New password  used for registration in Signup test
    String phone = "9090899089";//New phone number  used for registration in Signup test
    String Shortlistarea = "http://www.nobroker.in/property/sale/bangalore/Sanjaynagar?nbPlace=ChIJ85O6tekXrjsR13jdBn4Ge-c&lat_lng=13.0364641,77.5767631&radius=1.0/";//Paste the area link where you need to shortlist
    String url;
    public void geturl() {
        d = new FirefoxDriver();
        d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        d.get(baseurl);
    }


    public void loginnow() throws InterruptedException {

        //browser = new FirefoxDriver();
        System.out.println("----LOGGING IN----");
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
        url=browser.getCurrentUrl();
        //System.out.println(""+url);


    }

}






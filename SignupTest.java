package in.nobroker.automation.desktop.Webtest.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by RohitNaarayanan on 20/06/16.
 */
public class SignupTest extends TestSetup{

    //public WebDriver browser = new FirefoxDriver();
    public static WebDriver browser;

    Long end = System.currentTimeMillis();
    String email= Long.toString(end);
    String end1;



    @Test
    public void signup() throws InterruptedException

    {
        browser=new FirefoxDriver();
        System.out.println("!----------Sign up test-----------! ");
        System.out.println(end);
        browser.get(baseurl);
        browser.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        browser.findElement(By.linkText("Sign up")).click();
        Thread.sleep(5000);
        end1 = email.substring(0, Math.min(email.length(), 10));
        browser.findElement(By.id("contactNumber")).clear();
        browser.findElement(By.id("contactNumber")).sendKeys(end1);
        browser.findElement(By.xpath("(//button[@type='button'])[7]")).click();
        browser.findElement(By.cssSelector("form.signUpForm.form > input[name=\"name\"]")).clear();
      //  browser.findElement(By.cssSelector("form.signUpForm.form > input[name=\"name\"]")).sendKeys("signuptest");
        browser.findElement(By.cssSelector("form.signUpForm.form > input[name=\"email\"]")).clear();
        browser.findElement(By.cssSelector("form.signUpForm.form > input[name=\"email\"]")).sendKeys(email+"@gmail.com");
        browser.findElement(By.xpath("(//button[@type='button'])[13]")).click();




        try {
            if (By.id("registerError").findElement(browser).isDisplayed()) {

                String a = By.id("registerError").findElement(browser).getText();
                System.out.println("Test failed");
                System.out.println("Error :" + a);
                new AssertionError();
            }
        } catch (Exception e) {

            System.out.println("Test passed");

        }
       // browser.close();
        //browser.close();private boolean existsElement(String id) {

    }


}

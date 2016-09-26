package Web_test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignupTest extends TestSetup{

    //public WebDriver browser = new FirefoxDriver();
    public static WebDriver browser;


    @Test
    public void signup()

    {
        //browser=new FirefoxDriver();
        System.out.println("!----------Sign up test-----------! ");

        browser.get(baseurl);
        browser.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        browser.findElement(By.linkText("Sign up")).click();
        By.xpath("//*[@id=\"newAccountName\"]").findElement(browser).sendKeys(username);
        By.xpath("//*[@id=\"inputEmail\"]/input").findElement(browser).sendKeys(newaccemail);
        By.xpath("//*[@id=\"user_password\"]").findElement(browser).sendKeys(newpwd);
        By.xpath("//*[@id=\"newAccountPhone\"]").findElement(browser).sendKeys("9902340594");
        // browser.findElement(By.)
        By.xpath("//*[@id=\"registerUser\"]/div/a").findElement(browser).click();

        try {
            if (By.id("registerError").findElement(browser).isDisplayed()) {

                String a = By.id("registerError").findElement(browser).getText();
                System.out.println("Test failed");
                System.out.println("Error :" + a);
            }
        } catch (Exception e) {

            System.out.println("Test passed");

        }
        browser.close();
        //browser.close();private boolean existsElement(String id) {

    }


}



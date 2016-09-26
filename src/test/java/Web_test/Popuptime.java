package Web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by RohitNaarayanan on 10/08/16.
 */
public class Popuptime extends TestSetup {
    //public static WebDriver browser=new FirefoxDriver();

    @Test
    public void Popuptime() throws InterruptedException
    {

        browser.get(baseurl);
        By.id("nbCitySelector").findElement(browser).click();
        By.cssSelector("ul.cityselector a[data-key='bangalore']").findElement(browser).click();
        By.id("nbBHKSelector").findElement(browser).click();
        By.cssSelector("ul.dropdown [value='1']").findElement(browser).click();
        By.id("locationGoogle").findElement(browser).sendKeys("whit");
        Thread.sleep(5000);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ARROW_DOWN);
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(browser).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        By.id("searchButtonHomePage").findElement(browser).click();
        Thread.sleep(5000);

        By.xpath("//*[@id=\"contactOwnerBtnff80818150a577e10150a7be07ed04f4\"]").findElement(browser).click();
        browser.findElement(By.name("userName")).clear();
        browser.findElement(By.name("userName")).sendKeys("ro");
        browser.findElement(By.id("userEmail")).clear();
        browser.findElement(By.id("userEmail")).sendKeys("RN");
        browser.findElement(By.id("userEmail")).clear();
        browser.findElement(By.id("userEmail")).sendKeys("rn@gmail.com");
        browser.findElement(By.id("userPhone")).clear();
        browser.findElement(By.id("userPhone")).sendKeys("9000080000");
        long startTime = System.currentTimeMillis() / 1000;
        browser.findElement(By.id("contactOwnerSubmitButton")).click();
        //browser.get("http://google.com/");
        Thread.sleep(10000);
        WebElement search = browser.findElement(By.xpath("//button[@id='assurePlanButton'])[2]"));
        //Iterate through the loop as long as time(60sec) is with in the acceptable Page load time
        while(((System.currentTimeMillis()/1000)-startTime)<60){
            if(search.isDisplayed()){
                long endTime = System.currentTimeMillis()/1000;
                //System.out.println("The endTime is "+endTime);
                long loadTime = endTime - startTime - 5;
                System.out.println("\nTotaltime from contact owner pop up : " +loadTime + " seconds\n");
                break;

            }

        }

}}

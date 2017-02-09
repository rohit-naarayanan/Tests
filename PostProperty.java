package in.nobroker.automation.desktop.Webtest.firefox;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class PostProperty {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://beta.nobroker.in/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testPost() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("postproperty@gmail.com");
        driver.findElement(By.cssSelector("#user_new > #inputPassword > #user_password")).clear();
        driver.findElement(By.cssSelector("#user_new > #inputPassword > #user_password")).sendKeys("123456");
        Thread.sleep(5000);
        driver.findElement(By.id("signInButton")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("pypButHP")).click();
        ((FirefoxDriver)driver).getKeyboard().pressKey(Keys.F11);
        driver.findElement(By.id("propertyLocality")).clear();
        driver.findElement(By.id("propertyLocality")).sendKeys("Doml");
        By.xpath("//*[@id=\"locationGoogle\"]").findElement(driver).sendKeys(Keys.ARROW_DOWN);
       By.xpath("//*[@id=\"locationGoogle\"]").findElement(driver).sendKeys(Keys.ENTER);
        driver.findElement(By.id("rent")).clear();
        driver.findElement(By.id("rent")).sendKeys("10000");
        Thread.sleep(5000);
        driver.findElement(By.id("submitFrom")).click();
        driver.findElement(By.id("street")).clear();
        driver.findElement(By.id("street")).sendKeys("fs");
        driver.findElement(By.id("society")).click();
        driver.findElement(By.id("society")).clear();
        driver.findElement(By.id("society")).sendKeys("fdfd");
        driver.findElement(By.id("pinCode")).clear();
        driver.findElement(By.id("pinCode")).sendKeys("909090");
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.id("forLeaseNo")).click();
        driver.findElement(By.xpath("//form[@id='rentalForm']/div/div/div/div[2]/div[2]/div/label")).click();
        driver.findElement(By.id("deposit")).clear();
        driver.findElement(By.id("deposit")).sendKeys("2000");
        driver.findElement(By.xpath("//div[@id='datetimepicker1']/span/img")).click();
        driver.findElement(By.xpath("//div[@id='sizzle-1479715264687']/div/table/tbody/tr[3]/td[6]")).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Test");
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.id("totalFloor")).clear();
        driver.findElement(By.id("totalFloor")).sendKeys("2");
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.id("full_day")).click();
        driver.findElement(By.id("full_day")).click();
        driver.findElement(By.cssSelector("div.formInput.formDropdown > #slotType")).click();
        driver.findElement(By.xpath("//form[@id='schedulerForm']/div/div/div[2]/ul/li[3]")).click();
        driver.findElement(By.id("saveAndContinue")).click();
        driver.findElement(By.cssSelector("div.primaryButton")).click();
        driver.findElement(By.cssSelector("#airport > div.heading")).click();
        driver.findElement(By.cssSelector("#airport > div.heading")).click();
        driver.findElement(By.cssSelector("#hospital > div.heading")).click();
        driver.findElement(By.cssSelector("#hospital > div.heading")).click();
        driver.findElement(By.cssSelector("#movie_theater > div.heading")).click();
        driver.findElement(By.cssSelector("#movie_theater > div.heading")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
       // driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

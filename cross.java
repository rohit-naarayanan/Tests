package in.nobroker.automation.desktop.Webtest.firefox;// Getting started: http://docs.seleniumhq.org/docs/03_webdriver.jsp
// API details: https://github.com/SeleniumHQ/selenium#selenium 

// Unirest is the recommended way to interact with RESTful APIs in Java
// http://unirest.io/java.html

// runs test against http://crossbrowsertesting.github.io/selenium_example_page.html


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


class BasicTest {

    static String username = "support%40nobroker.in"; // Your username
    static String authkey = "u4277de46c8e94ee";  // Your authkey
    String testScore = "unset";

    public static void main(String[] args) throws Exception
    {
        BasicTest myTest = new BasicTest();

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("name", "Basic Example");
        caps.setCapability("build", "1.0");
        caps.setCapability("browser_api_name", "Edge20");
        caps.setCapability("os_api_name", "Win10");
        caps.setCapability("screen_resolution", "1024x768");
        caps.setCapability("record_video", "true");
        caps.setCapability("record_network", "true");


        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
        System.out.println(driver.getSessionId());

        // we wrap the test in a try catch loop so we can log assert failures in our system
        //      try {

        {
        // load the page url
        System.out.println("Loading Url");
        driver.get("https://nobroker.in");
        System.out.println("----LOGIN TEST----");
        // browser2.get(baseurl);
        Thread.sleep(5000);
        By.linkText("Log in").findElement(driver).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"userName\"]").findElement(driver).sendKeys("8951321922");

        /**Using CSS selector since id for paswword field is same**/

        By.cssSelector("#dosignin #user_password").findElement(driver).sendKeys("Password@123");
        Thread.sleep(10000);
        //browser2.findElement(By.id("signInButton")).click();
        By.id("signInButton").findElement(driver).click();
        Thread.sleep(10000);

        //assertTrue(!isElementPresent(By.linkText("Empresas en Misión")));
        if (driver.findElements(By.id("userProfileDD")).isEmpty()) {
            driver.close();
            //throw new IllegalStateException("Login error");
            System.out.println("Login fail");
        } else {
            System.out.println("Login succesfull");
            myTest.testScore = "pass";
            driver.close();
        }

            // maximize the window - DESKTOPS ONLY
            //System.out.println("Maximizing window");
            //driver.manage().window().maximize();

            // Check the page title (try changing to make the assertion fail!)
            //System.out.println("Checking title");
            //assertEquals(driver.getTitle(), "Selenium Test Example Page");

            // if we get to this point, then all the assertions have passed
            // that means that we can set the score to pass in our system

        }
       // catch(AssertionError ae) {

            // if we have an assertion error, take a snapshot of where the test fails
//            // and set the score to "fail"
//            String snapshotHash = myTest.takeSnapshot(driver.getSessionId().toString());
//            myTest.setDescription(driver.getSessionId().toString(), snapshotHash, ae.toString());
//            myTest.testScore = "fail";
////        //}
//        finally {
//
//            System.out.println("Test complete: " + myTest.testScore);
//
//            // here we make an api call to actually send the score
//            myTest.setScore(driver.getSessionId().toString(), myTest.testScore);
//
//            // and quit the driver
//            driver.quit();
//        }
    }

    public JsonNode setScore(String seleniumTestId, String score) throws UnirestException {
        // Mark a Selenium test as Pass/Fail
        HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                .basicAuth(username, authkey)
                .routeParam("seleniumTestId", seleniumTestId)
                .field("action","set_score")
                .field("score", score)
                .asJson();
        return response.getBody();
    }

    public String takeSnapshot(String seleniumTestId) throws UnirestException {
        /*
         * Takes a snapshot of the screen for the specified test.
         * The output of this function can be used as a parameter for setDescription()
         */
        HttpResponse<JsonNode> response = Unirest.post("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots")
                .basicAuth(username, authkey)
                .routeParam("seleniumTestId", seleniumTestId)
                .asJson();
        // grab out the snapshot "hash" from the response
        String snapshotHash = (String) response.getBody().getObject().get("hash");

        return snapshotHash;
    }

    public JsonNode setDescription(String seleniumTestId, String snapshotHash, String description) throws UnirestException{
        /* 
         * sets the description for the given seleniemTestId and snapshotHash
         */
        HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots/{snapshotHash}")
                .basicAuth(username, authkey)
                .routeParam("seleniumTestId", seleniumTestId)
                .routeParam("snapshotHash", snapshotHash)
                .field("description", description)
                .asJson();
        return response.getBody();
    }
}
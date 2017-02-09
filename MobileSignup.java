package in.nobroker.automation.desktop.Webtest.firefox;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


class MobileSignup {

    static String username = "support%40nobroker.in"; // Your username
    static String authkey = "u4277de46c8e94ee";  // Your authkey
    String testScore = "unset";

    public static void main(String[] args) throws Exception {
        Long end = System.currentTimeMillis();
        String email= Long.toString(end);
        String end1;

        BasicTest myTest = new BasicTest();

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("name", "Basic Example");
        caps.setCapability("build", "1.0");
        caps.setCapability("browser_api_name", "MblChrome35");
        caps.setCapability("os_api_name", "GalaxyS5-And44");
        caps.setCapability("screen_resolution", "1080x1920");
        caps.setCapability("record_video", "true");
        caps.setCapability("record_network", "true");


        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub"), caps);
        System.out.println(driver.getSessionId());

        // we wrap the test in a try catch loop so we can log assert failures in our system
//        try {

        driver.get("https://beta.nobroker.in/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//header[@id='header']/div/nav/div/a/i")).click();
        Thread.sleep(10000);

        driver.findElement(By.linkText("Login/Signup")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("sidenav-overlay")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Sign Up")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("mobileNum")).clear();
        end1 = email.substring(0, Math.min(email.length(), 10));
        driver.findElement(By.id("mobileNum")).sendKeys(end1);
        driver.findElement(By.id("nextButton")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("name")).clear();

        driver.findElement(By.id("name")).sendKeys("signuptest");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email+"@gmail.com");
        Thread.sleep(5000);
        driver.findElement(By.id("doneButton")).click();

        System.out.println("Test passed");
        driver.quit();

        // myTest.testScore = "pass";
//        }
//        catch(AssertionError ae) {
//
//            // if we have an assertion error, take a snapshot of where the test fails
//            // and set the score to "fail"
//            String snapshotHash = myTest.takeSnapshot(driver.getSessionId().toString());
//            myTest.setDescription(driver.getSessionId().toString(), snapshotHash, ae.toString());
//            myTest.testScore = "fail";
//        }
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
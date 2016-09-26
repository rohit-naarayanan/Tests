package Web_test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * Created by RohitNaarayanan on 08/07/16.
 */
public class Email extends TestSetup {

    private static  AndroidDriver driver =null;

    public static void main(String[] args) {
        setup();
    }

    @BeforeTest
    public static void setup() {
        File appDir = new File("/Users/rohitnaarayanan/Downloads");
        File app = new File(appDir, "app-proFlavor-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "5.1.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "MainActivity");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

    }
}




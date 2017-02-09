package in.nobroker.automation.desktop.Webtest.firefox;

import in.nobroker.core.util.AppUtils;
import in.nobroker.core.util.HashUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class PageLoad extends TestSetup {



    @Test
    public void PageLoad() throws InterruptedException {
        browser.get("http://nobroker.in");
        Thread.sleep(10000);
        //By.id("userProfileDD").findElement(browser).click();
        //browser.findElement(By.linkText("Sign Out")).click();
        //System.out.println("----LOGGING IN----");

        Thread.sleep(5000);
        By.linkText("Log in").findElement(browser).click();
        Thread.sleep(5000);
        By.xpath("//*[@id=\"userName\"]").findElement(browser).sendKeys(un);

        /**Using CSS selector since id for paswword field is same**/

        By.cssSelector("#dosignin #user_password").findElement(browser).sendKeys(pwd);
//        By.xpath("//*[@id=\"user_password\"]").findElement(browser).sendKeys("rohit.naarayanan92@gmail.com");
        Thread.sleep(5000);
        long startTime1 = System.currentTimeMillis() / 1000;

        By.id("signInButton").findElement(browser).click();
        Thread.sleep(10000);
        long endTime1 = System.currentTimeMillis() / 1000;
        long loadTime1 = endTime1 - startTime1;

        String loginLoadTimeStr = "Total login time: " + loadTime1 + " seconds\n";
        System.out.println(loginLoadTimeStr);

        browser.get("http://nobroker.in/tenant/plans");
        Thread.sleep(10000);
        long startTime = System.currentTimeMillis();
        //browser.findElement(By.cssSelector("div.overlay.jx_ui_Widget")).click();
        browser.findElement(By.xpath("(//button[@id='assurePlanButton'])[2]")).click();
        //Thread.sleep();

        // WebElement search = browser.findElement(By.linkText("Log in"));
        //Iterate through the loop as long as time(60sec) is with in the acceptable Page load time
        long endTime = System.currentTimeMillis();
        //System.out.println("The endTime is " + endTime);
        long loadTime = endTime - startTime;

        String paymentLoadTimeStr = "Payment Load time: " + loadTime + " mil seconds\n";
        System.out.println(paymentLoadTimeStr);

        Thread.sleep(10000);
        String url = browser.getCurrentUrl();

        String paymentUrl = "Payment url: " + url + "\n";
        System.out.println(paymentUrl);

        browser.close();

        String authorization="";
        String mailBody="";
        String fromAddress="";
        String subject="";
        String recipientName="";
        String recipientEmail="";


        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        authorization = HashUtils.generateMD5Hash(date + AppUtils.NOBROKER_SALT);

        mailBody = loginLoadTimeStr + paymentLoadTimeStr + paymentUrl;
        mailBody = mailBody.replaceAll("(\r\n|\n)", "<br />");

        fromAddress = "rohit.naarayanan@nobroker.in";
        subject = "Page Load Time : " + date;
        //recipientName+"Engineering"
        recipientName = "NB Engineering";
        //recipientEmail="engg@nobroker.in";
        recipientEmail = "rohit.naarayanan@nobroker.in";

        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        FormDataBodyPart formDataBodyPart1 = new FormDataBodyPart("authorization", authorization);
        FormDataBodyPart formDataBodyPart2 = new FormDataBodyPart("mailBody", mailBody);
        FormDataBodyPart formDataBodyPart3 = new FormDataBodyPart("fromAddress", fromAddress);
        FormDataBodyPart formDataBodyPart4 = new FormDataBodyPart("subject", subject);
        FormDataBodyPart formDataBodyPart5 = new FormDataBodyPart("recipient_name", recipientName);
        FormDataBodyPart formDataBodyPart6 = new FormDataBodyPart("recipient_email", recipientEmail);


        formDataMultiPart.bodyPart(formDataBodyPart1);
        formDataMultiPart.bodyPart(formDataBodyPart2);
        formDataMultiPart.bodyPart(formDataBodyPart3);
        formDataMultiPart.bodyPart(formDataBodyPart4);
        formDataMultiPart.bodyPart(formDataBodyPart5);
        formDataMultiPart.bodyPart(formDataBodyPart6);

        String mailAPIUrl = "http://www.nobroker.in/api/v1/notification/email/send";

        ClientConfig config = new ClientConfig();
        config.register(MultiPartFeature.class);

        Response response = newClient(config).target(mailAPIUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));

        if(response.getStatus()==200){
            System.out.println("Mail sent successfully at " + Calendar.getInstance().getTime());
        }else {
            System.out.println("Mail not sent at " + Calendar.getInstance().getTime());
            System.out.println(response);
        }

    }
}

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

/**
 * Created by RohitNaarayanan on 15/11/16.
 */
public class PaymentLoadTime extends TestSetup {

    @Test
    public void NewContactOwner() throws InterruptedException {
      //  browser.get("https://www.nobroker.in/property/3-BHK-apartment-for-rent-in-HSR-Layout-bangalore-for-rs-28000/ff8081815083f3ee01508458586b0494/detail");
        browser.get("https://www.nobroker.in/property/3-BHK-apartment-for-rent-in-HSR-Layout-bangalore-for-rs-25000/ff808181593a884001593b605f603a51/detail");
        Thread.sleep(10000);
//
        browser.findElement(By.xpath("(//button[@id='contactOwnerBtnff808181593a884001593b605f603a51'])[2])")).click();

        //browser.findElement(By.xpath("(//button[@id='contactOwnerBtnff808181593a884001593b605f603a51'])[2]")).click();
        Thread.sleep(10000);
        browser.findElement(By.id("contactNumber")).clear();
        browser.findElement(By.id("contactNumber")).sendKeys("9889898997");
        Thread.sleep(10000);


        browser.findElement(By.xpath("(//button[@type='button'])[7]")).click();

        Thread.sleep(10000);
        browser.findElement(By.id("passwordRadio")).click();
        browser.findElement(By.xpath("//form[@id='checkMobileNumber']/div[2]/fieldset/div[2]/label/span")).click();
        browser.findElement(By.cssSelector("fieldset.passwordInputField.passwordGroup > input[name=\"password\"]")).clear();
        browser.findElement(By.cssSelector("fieldset.passwordInputField.passwordGroup > input[name=\"password\"]")).sendKeys("123456");
        browser.findElement(By.xpath("(//button[@type='button'])[11]")).click();
        Thread.sleep(5000);
        long start=System.currentTimeMillis();
        browser.findElement(By.xpath("(//button[@id='assurePlanButton'])[2]")).click();
        //long end=System.currentTimeMillis()/1000;
        //long LoadTime=end-start;
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        long loadTime = end - start-5000;
        Thread.sleep(10000);
        String url = browser.getCurrentUrl();
        String paymentLoadTimeStr = "Payment Load time: " + loadTime + " mil seconds\n ";
        System.out.println(paymentLoadTimeStr);
        //System.out.println(url);
        String ccavenue="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction";
        String Razorpay="Razorpay";
        String paymentUrl="Payment url is " + url + "\n";
        //browser close
        if ((url == ccavenue))
        {
            //String paymentUrl = "Payment url is CC AVenue: \n";
            String paymenttype="Payment url is  ";
            System.out.println(paymenttype);

        }
        else {
            String paymenttype="Payment url is  CC avenue";
            System.out.println(paymenttype);
            // System.out.println("Payment is razorpay");
            String authorization = "";
            String mailBody = "";
            String fromAddress = "";
            String subject = "";
            String recipientName = "";
            String recipientEmail = "";


            String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            authorization = HashUtils.generateMD5Hash(date + AppUtils.NOBROKER_SALT);

            mailBody = paymentLoadTimeStr + url;
            mailBody = mailBody.replaceAll("(\r\n|\n)", "<br />");

            fromAddress = "rohit.naarayanan@nobroker.in";
            subject = "Page Load Time : " + date;
            //recipientName+"Engineering"
           // recipientName = "NB Engineering";
            recipientEmail = "rohit.naarayanan@nobroker.in";
            recipientEmail = "engg@nobroker.in";

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

            if (response.getStatus() == 200) {
                System.out.println("Mail sent successfully at " + Calendar.getInstance().getTime());
            } else {
                System.out.println("Mail not sent at " + Calendar.getInstance().getTime());
                System.out.println(response);
            }




        }



    }


}
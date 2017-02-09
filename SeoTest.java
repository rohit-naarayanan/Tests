package in.nobroker.automation.desktop.Webtest.firefox;

import com.mongodb.*;
import in.nobroker.core.dao.DaoException;
import in.nobroker.core.util.AppUtils;
import in.nobroker.core.util.HashUtils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class SeoTest{


    public String[][] data = null;
    Connection.Response response = null;

    private MongoClient mClient;

    private String MONGO_HOST = "139.59.32.212";
    private int MONGO_PORT = 27017;

    private String dbName = "test";
    private String mongoUserName = "marketingAutomation";
    private String mongoPassword = "welcome1";


    public void initMongoDB() throws DaoException {
        MongoClientOptions clientOptions = new MongoClientOptions.Builder()
                .connectionsPerHost(40)
                // This setting will close the cursor if they open by client.
                .cursorFinalizerEnabled(true)
                .autoConnectRetry(true)
                .connectTimeout(60000)
                .socketTimeout(60000)
                .build();

        try {
            ServerAddress address = new ServerAddress(MONGO_HOST, MONGO_PORT);
            mClient = new MongoClient( address, clientOptions);
        } catch (Exception e) {
            System.out.println("Error occured while fetching client instance "+ MONGO_HOST +" " + MONGO_PORT);
            System.out.println(e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public boolean isInitailized()
    {
        return mClient == null  ? false : true;
    }

    public boolean isAuthorizedAccess(DB dbName){
        return dbName.authenticate(mongoUserName, mongoPassword.toCharArray() );
    }

    public DB getDatabase( String dbName ) throws DaoException {
        if(!isInitailized()) {
            initMongoDB();
        }
        DB db = mClient.getDB(dbName);
        if( ! isAuthorizedAccess( db )) {
            throw new DaoException("Could not authenticate User " + mongoUserName, null);
        }
        return db;
    }


    private DB getDBConnection() {
        try {
            return getDatabase(dbName);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not get nobroker mongo client");
        }
    }

    public void saveData(String collectionName, Map<String, Object> map)
    {

            try {
                DB nobrokerDb = getDBConnection();
                DBCollection marketingCollection = nobrokerDb.getCollection(collectionName);
                BasicDBObject basicDBObject = new BasicDBObject(map);
                marketingCollection.insert(basicDBObject);
            }
            catch (Exception e){

                System.out.println("Mongo Exception :" + e);
                getcount(collectionName);
            }

    }


    public void getcount(String collectionName){
        Long c=mClient.getDB("test").getCollection(collectionName.replace(".xls","")).count();
        System.out.println("URL count:"+c);
       // String total1 ="total URL parsed :"+ c;
        BasicDBObject query=new BasicDBObject();
        query.put("status",500);
        BasicDBObject query1=new BasicDBObject();
        query1.put("status",404);
        BasicDBObject query2=new BasicDBObject();
        query2.put("status",200);
        long error=mClient.getDB("test").getCollection(collectionName.replace(".xls","")).count(query);
        long broken=mClient.getDB("test").getCollection(collectionName.replace(".xls","")).count(query1);
        long total=mClient.getDB("test").getCollection(collectionName.replace(".xls","")).count(query2);
        String count="total URL parsed : "+ c+"\n";
        System.out.println("500 Errors :"+error);
        String error1="500 error : "+error+"\n";
        System.out.println("Broken links :" +broken +"\n");
        String broken1="Broken links :" +broken+"\n";
        System.out.println("Status 200 :"+total);
        String sucess="Status 200 : "+total+"\n";
        String authorization="";
        String mailBody="";
        String fromAddress="";
        String subject="";
        String recipientName="";
        String recipientEmail="";


        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        authorization = HashUtils.generateMD5Hash(date + AppUtils.NOBROKER_SALT);

        mailBody = count+ error1 +broken1  + sucess;
        mailBody = mailBody.replaceAll("(\r\n|\n)", "<br />");

        fromAddress = "rohit.naarayanan@nobroker.in";
        subject = "SEO Tests : " + collectionName;
        //recipientName+"Engineering"
        recipientName = "NB Engineering";
        recipientEmail="engg@nobroker.in";
        // recipientEmail = "rohit.naarayanan@nobroker.in";

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

    public String[][] process(String fileName, InputStream is) throws IOException
    {

        System.out.println("Processing file "+fileName);
        Workbook w;

        try
        {
            w = Workbook.getWorkbook(is);
            // Get the first sheet


            Sheet sheet = w.getSheet(0);
            data = new String[sheet.getColumns()][sheet.getRows()];
            for (int j = 0; j <sheet.getColumns(); j++)
            {
                for (int i = 0; i < sheet.getRows(); i++)
                {
                    Cell cell = sheet.getCell(j, i);
                    data[j][i] = cell.getContents();
                    //  System.out.println(cell.getContents());
                }
            }

//            for (int j = 0; j < data.length; j++) {
            int j=0;
            for (int i = 0; i < data[j].length; i++)
            {

                System.out.println("\n" + data[j][i]);

                String url = data[j][i];
                String Impressions=data[4][i];
                String clicks=data[3][i];
                String CampaignName=data[2][i];
                String Adgroup=data[1][i];
//                    // String Searchtem=data[4][i];
//                    // System.out.println(clicks);
                    System.out.println("\n"+"Clicks on page"+ ":"+clicks);
                System.out.println("\n"+"campaign name"+":"+CampaignName);
                System.out.println("\n"+"Add group"+":"+Adgroup);
                Document doc;
                try {
                    long starttime=System.currentTimeMillis();
                    Thread.sleep(1000);

                    doc = Jsoup.connect(data[j][i]).userAgent("Nobroker-bot").validateTLSCertificates(false).timeout(10 * 10000).get();
                    try{

                        response = Jsoup.connect(data[j][i]).validateTLSCertificates(false).timeout(10 * 10000).execute();
                        System.out.println("Time taken : " + (System.currentTimeMillis() - starttime) );
                    }

                    catch (IllegalArgumentException e)
                    {
                        e.printStackTrace();
                        continue;
                    }
                }


                catch (HttpStatusException e){
                    e.printStackTrace();
                    System.out.println("Invalid url"+ data[j][i]);
                    continue;
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Invalid url"+ data[j][i]);
                    continue;
                }
                int status = response.statusCode();
                System.out.println(status);
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String title=doc.title();
                System.out.println("Page title: "+title);
                dataMap.put("url", data[j][i]);
                if(response.statusCode()!=400)

                {


                    //System.out.println("\n" + "Response" + "\n" + status + ""+"Is URL throwing 500 : " + response.url().toString().contains("https://www.nobroker.in/static/error/500.html"));
                    if (response.url().toString().contains("https://www.nobroker.in/static/error/500.html") != true )
                    {
                        if

                                (doc.html().contains("Page Not Found.") != true)
                        {
                            try {


                                String keywords = doc.select("meta[name=keywords]").first().attr("content");
                                System.out.println("Meta keyword : " + "\n" + keywords);
                                String description = doc.select("meta[name=description]").get(0).attr("content");
                                System.out.println("Meta description : " + "\n" + description);
                                Elements elem = doc.select(".h1Title");
                                String Pagetitle = elem.text();
                                System.out.println("\n" + "\"Title of the page:" + Pagetitle);
                                Elements elem1 = doc.select(".h1Title #resultsCount");
                                String properties = elem1.text();
                                System.out.println("\n" + "No of properties :" + properties);
                                status = 200;
                                System.out.println("Response Code" + "\n" + status);

                                dataMap.put("status", status);
                                dataMap.put("keywords", keywords);
                                dataMap.put("description", description);
                                dataMap.put("No of properties", properties);
                                dataMap.put("Title", Pagetitle);
                               dataMap.put("Number of clicks",clicks);
                                dataMap.put("Campaign name", CampaignName);
                                dataMap.put("Ad group", Adgroup);
                                //dataMap.put("Search Term",Searchtem);
                                dataMap.put("Impressions",Impressions);
                            }
                            catch (NullPointerException e)
                            {
                                e.printStackTrace();
                                continue;
                            }
                            }


                        else {
                            System.out.println("Link is broken");
                            status=404;
                            System.out.println("Response Code"+"\n"+status);

                            dataMap.put("status", status);
                                dataMap.put("Number of clicks",clicks);
                            dataMap.put("Campaign name",CampaignName);
                            dataMap.put("Ad group",Adgroup);
                            dataMap.put("Impressions",Impressions);
                        }

                    }
                    else {
                        System.out.println("500 error");
                        status=500;
                        System.out.println("Response Code"+"\n"+status);

                        dataMap.put("status", status);
                           dataMap.put("Number of clicks",clicks);
                        dataMap.put("Campaign name",CampaignName);
                        dataMap.put("Ad group",Adgroup);
                        dataMap.put("Impressions",Impressions);
                    }

                    saveData(fileName.replace(".xls",""), dataMap);
                } // for(i) loop ends
                else{
                    System.out.println("400 error");
                    status=400;
                    System.out.println("Response Code"+"\n"+status);

                    dataMap.put("status", status);
                        dataMap.put("Number of clicks",clicks);
                    dataMap.put("Campaign name",CampaignName);
                    dataMap.put("Ad group",Adgroup);
                    dataMap.put("Impressions",Impressions);
                }
            }

            // } // for(j) loop ends
//
        }
        catch (BiffException e)
        {
            e.printStackTrace();
        }
        return data;
    }


public static void main(String[] args) throws IOException {
        SeoTest test = new SeoTest();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:*.xls");
        for(Resource resource: resources)
        {
            test.process(resource.getFilename(), resource.getInputStream());
            test.getcount(resource.getFilename().replace(".xls",""));
        }


    }
}
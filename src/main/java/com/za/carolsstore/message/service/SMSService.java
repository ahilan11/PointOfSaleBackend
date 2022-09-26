package com.za.carolsstore.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.WebTarget;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SMSService implements iSMSService{
    private WebTarget webTarget;
    private Client client;
    private ObjectMapper om;
    private File configFile = new File("C:\\Users\\Liam\\Desktop\\Java\\Carol-s-Newtique"
            + "\\com.carolsboutique_PointOfSale_war_1.0-SNAPSHOT\\src\\main\\java\\com\\carolsboutique\\"
            + "pointofsale\\Email\\Service\\RestURLConfig.json");

    public SMSService() {
        this.om = new ObjectMapper();
        client = ClientBuilder.newClient();
    }

    @Override
    public String sendSMS(LocalDateTime dateTime, String toPhoneNumber, String message) {
        Response response = null;
        String url = readURL() + "/sms/sms_request";
        webTarget = client.target(url);
        
        String xml = "<smsreq>\n" +
            "<datetime>" + dateTime + "</datetime>\n" +
            "<user>GROUP1</user>\n" +
            "<pass>group1</pass>\n" +
            "<msisdn>" + toPhoneNumber + "</msisdn>\n" +
            "<message>" + message + "</message>\n" +
            "</smsreq>";
        
        response = webTarget.request().post(Entity.xml(xml));
        String smsResp = response.readEntity(String.class);
        return smsResp;
    }
    
    private String readURL(){
        SMSService.RestURL url = null;
        try {
            url = om.readValue(configFile, SMSService.RestURL.class);
        } catch (IOException ex) {
            Logger.getLogger(SMSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url.toString();
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class RestURL{
        private String protocol;
        private String ip;
        private String port;
        
        @Override
        public String toString() {
            return protocol + "://" + ip + ":" + port;
        }
    }
}

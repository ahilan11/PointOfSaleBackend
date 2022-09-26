package com.za.carolsstore.message.service;



import com.za.carolsstore.employee.service.EmployeeServiceImp;
import com.za.carolsstore.employee.service.iEmployeeService;
import com.za.carolsstore.identity.service.IdentityGenerator;
import com.za.carolsstore.identity.service.iIdentityGenerator;
import com.za.carolsstore.message.dao.EmailDaoImp;
import com.za.carolsstore.message.dao.iEmailDao;
import com.za.carolsstore.message.model.Email;
import com.za.carolsstore.sale.model.SaleLineItem;
import com.za.carolsstore.sale.service.SaleService;
import com.za.carolsstore.sale.service.iSaleService;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailService implements iEmailService{
    private iIdentityGenerator idGen = new IdentityGenerator();
    private iEmailDao dao = new EmailDaoImp();
    private iSaleService dao2 = new SaleService();
    private iEmployeeService dao3 = new EmployeeServiceImp();
    private final String from = "Carolboutique33@gmail.com";
    private final String username = "Carolboutique33";
    private final String password = "trridntdkgkovphq";
    private String host = "smtp.gmail.com";
    private final String port = "25";

    @Override
    public String sendEmailRecipt(String toEmail, String saleID) {
        String response = null;
        Properties prop = new Properties();
        
        List<SaleLineItem> lineItems = dao2.getSaleLineItems(saleID);

        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "true");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });
        
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            
            message.setSubject("Testing Email");
            
            message.setContent("<h1>Receipt</h1>", "text/html");
            
            Transport.send(message);
            
            Email email = new Email(null, toEmail, "receipt",LocalDate.now(), LocalTime.now());
            String ID = idGen.generateID(email);
            email.setId(ID);
            response = addEmail(email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public String sendEmailNotifiction(String toEmail) {
        String response = null;
        Properties prop = new Properties();
        
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "true");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });
        
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            
            message.setSubject("Testing Email");
            
            message.setContent("<h1>Notification</h1>", "text/html");
            
            Transport.send(message);
            
            Email email = new Email(null, toEmail, "receipt", LocalDate.now(), LocalTime.now());
            String ID = idGen.generateID(email);
            email.setId(ID);
            response = addEmail(email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public String addEmail(Email email) {
        boolean emailSent = dao.addEmail(email);
        if(emailSent == true){
            return "Email Sent successfuly";
        } else {
            return "Email Could not Be Sent";
        }
    }
    
    private String createReceipt(List<SaleLineItem>lineItems){
        String quantity = "";
        String productName = "";
        String productPrice = "";
        String receipt = "<!doctype html>\n" +
"    <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"      <head>\n" +
"        <title>\n" +
"          RECEIPT\n" +
"        </title>\n" +
"        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <style type=\"text/css\">\n" +
"          #outlook a { padding:0; }\n" +
"          body { margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%; }\n" +
"          table, td { border-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt; }\n" +
"          img { border:0;height:auto;line-height:100%; outline:none;text-decoration:none;-ms-interpolation-mode:bicubic; }\n" +
"          p { display:block;margin:13px 0; }\n" +
"        </style>\n" +
"        \n" +
"        <link href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
"<link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
"        <style type=\"text/css\">\n" +
"          @import url(https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700);\n" +
"@import url(https://fonts.googleapis.com/css?family=Cabin:400,700);\n" +
"        </style>\n" +
"\n" +
"    \n" +
"        \n" +
"    <style type=\"text/css\">\n" +
"      @media only screen and (max-width:480px) {\n" +
"        .mj-column-per-100 { width:100% !important; max-width: 100%; }\n" +
".mj-column-per-50 { width:50% !important; max-width: 50%; }\n" +
".mj-column-per-25 { width:25% !important; max-width: 25%; }\n" +
"      }\n" +
"    </style>\n" +
"    \n" +
"  \n" +
"        <style type=\"text/css\">\n" +
"        \n" +
"        \n" +
"        </style>\n" +
"        <style type=\"text/css\">.hide_on_mobile { display: none !important;} \n" +
"        @media only screen and (min-width: 480px) { .hide_on_mobile { display: block !important;} }\n" +
"        .hide_section_on_mobile { display: none !important;} \n" +
"        @media only screen and (min-width: 480px) { \n" +
"            .hide_section_on_mobile { \n" +
"                display: table !important;\n" +
"            } \n" +
"\n" +
"            div.hide_section_on_mobile { \n" +
"                display: block !important;\n" +
"            }\n" +
"        }\n" +
"        .hide_on_desktop { display: block !important;} \n" +
"        @media only screen and (min-width: 480px) { .hide_on_desktop { display: none !important;} }\n" +
"        .hide_section_on_desktop { \n" +
"            display: table !important;\n" +
"            width: 100%;\n" +
"        } \n" +
"        @media only screen and (min-width: 480px) { .hide_section_on_desktop { display: none !important;} }\n" +
"        \n" +
"          p, h1, h2, h3 {\n" +
"              margin: 0px;\n" +
"          }\n" +
"\n" +
"          ul, li, ol {\n" +
"            font-size: 11px;\n" +
"            font-family: Ubuntu, Helvetica, Arial;\n" +
"          }\n" +
"\n" +
"          a {\n" +
"              text-decoration: none;\n" +
"              color: inherit;\n" +
"          }\n" +
"\n" +
"          @media only screen and (max-width:480px) {\n" +
"\n" +
"            .mj-column-per-100 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-75 { width:75%!important; max-width:75%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-60 { width:60%!important; max-width:60%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-50 { width:50%!important; max-width:50%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-40 { width:40%!important; max-width:40%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-33 { width:33.333333%!important; max-width:33.333333%!important; }\n" +
"            .mj-column-per-100 > .mj-column-per-25 { width:25%!important; max-width:25%!important; }\n" +
"\n" +
"            .mj-column-per-100 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-75 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-60 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-50 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-40 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-33 { width:100%!important; max-width:100%!important; }\n" +
"            .mj-column-per-25 { width:100%!important; max-width:100%!important; }\n" +
"        }</style>\n" +
"        \n" +
"      </head>\n" +
"      <body style=\"background-color:#FFFFFF;\">\n" +
"        \n" +
"        \n" +
"      <div style=\"background-color:#FFFFFF;\">\n" +
"        \n" +
"      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#c3c3c3;background-color:#c3c3c3;width:100%;\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td>\n" +
"              \n" +
"        \n" +
"      \n" +
"    \n" +
"        \n" +
"      <div style=\"margin:0px auto;max-width:600px;\">\n" +
"        \n" +
"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"          <tbody>\n" +
"            <tr>\n" +
"              <td style=\"direction:ltr;font-size:0px;padding:27px 0px 27px 0px;text-align:center;\">\n" +
"               \n" +
"            \n" +
"      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"center\" style=\"font-size:0px;padding:10px 20px 10px 20px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:14px;line-height:1.5;text-align:center;color:#FFFFFF;\"><h1 style=\"font-family: 'Cabin', sans-serif; color: #FFFFFF; font-size: 32px;\"><span style=\"font-size: 48px; font-family: Georgia, sans-serif; color: #FFFFFF;\">\n" +
"                  <img alt=\"company logo\" src=\"C:\\Users\\nicad\\OneDrive\\Desktop\\VZAP FINAL PRODUCT\\ClientPOS\\src\\main\\java\\com\\carolsboutique\\clientpos\\Logo\\Carols Boutique logo.png\" style=\"width: 457.8px; height: 195.3px;\">\n" +
"              </span></h1></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"   \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"   \n" +
"  \n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#000000;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: center;\"><span style=\"color: #000000;\"><strong><span style=\"font-size: 18px;\">" + lineItems.get(0).getSaleID() + "</span></strong></span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"         \n" +
"              </td>\n" +
"            </tr>\n" +
"          </tbody>\n" +
"        </table>\n" +
"        \n" +
"      </div>\n" +
"    \n" +
"        \n" +
"      \n" +
"    \n" +
"      \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td>\n" +
"              \n" +
"        \n" +
"     \n" +
"        \n" +
"      <div style=\"margin:0px auto;max-width:600px;\">\n" +
"        \n" +
"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"          <tbody>\n" +
"            <tr>\n" +
"              <td style=\"direction:ltr;font-size:0px;padding:9px 0px 9px 0px;text-align:center;\">\n" +
"               \n" +
"            \n" +
"      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#000000;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: left;\"><span style=\"font-size: 14px;\">Teller: " + dao3.getEmployee(dao2.getSale(lineItems.get(0).getSaleID()).getEmployeeID()).getName() + "</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"         \n" +
"            \n" +
"      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#000000;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"font-size: 14px;\">" + dao2.getSale(lineItems.get(0).getSaleID()).getDateSold() + "</span></p>\n" +
"<p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"font-size: 14px;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; " + dao2.getSale(lineItems.get(0).getSaleID()).getTimeSold() + "</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"         \n" +
"              </td>\n" +
"            </tr>\n" +
"          </tbody>\n" +
"        </table>\n" +
"        \n" +
"      </div>\n" +
"    \n" +
"        \n" +
"     \n" +
"    \n" +
"      \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#c3c3c3;background-color:#c3c3c3;width:100%;\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td>\n" +
"              \n" +
"        \n" +
"     \n" +
"        \n" +
"      <div style=\"margin:0px auto;max-width:600px;\">\n" +
"        \n" +
"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"          <tbody>\n" +
"            <tr>\n" +
"              <td style=\"direction:ltr;font-size:0px;padding:10px 0px 10px 0px;text-align:center;\">\n" +
"               \n" +
"            \n" +
"      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: left;\"><span style=\"color: #660066;\">QTY</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"   \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"   \n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
//"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial;\"><span style=\"color: #660066;\">1</span></p></div>\n" +
                quantity +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"  \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"       \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"        \n" +
"            \n" +
"      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: left;\"><span style=\"color: #660066;\">DESC</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"   \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"  \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
//"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial;\"><span style=\"color: #660066;\">Long Sleeve Shirt</span></p></div>\n" +
                productName +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"   \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"  \n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"  \n" +
"  \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"   \n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"   \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
" \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
"    \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"  \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"                \n" +
"      \n" +
" \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"   \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">AMT/UNIT</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"   \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
" \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
//"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R100.00</span></p></div>\n" +
                productPrice +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
" \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"  \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">SUBTOTAL:</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">VAT:</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">TOTAL:</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"         \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">AMOUNT:</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">CHANGE:</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td style=\"vertical-align:top;padding:0px 0px 0px 0px;\">\n" +
"              \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">AMT/UNIT</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"           \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R100.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"              \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R100.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R14.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R114.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;word-break:break-word;\">\n" +
"          \n" +
"      <div style=\"height:50px;\">\n" +
"        &nbsp;\n" +
"      </div>\n" +
"      \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R120.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R6.00</span></p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          </tbody>\n" +
"        </table>\n" +
"        \n" +
"      </div>\n" +
"    \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td>\n" +
"              \n" +
"      <div style=\"margin:0px auto;max-width:600px;\">\n" +
"        \n" +
"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"          <tbody>\n" +
"            <tr>\n" +
"              <td style=\"direction:ltr;font-size:0px;padding:9px 0px 9px 0px;text-align:center;\">\n" +
"             \n" +
"      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"left\" style=\"font-size:0px;padding:15px 15px 15px 15px;word-break:break-word;\">\n" +
"                \n" +
"      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#000000;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: center;\">Returns accepted within 10 days with receipt only</p></div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"            <tr>\n" +
"              <td style=\"font-size:0px;padding:10px 54px;padding-top:10px;word-break:break-word;\">\n" +
"                \n" +
"      <p style=\"font-family: Ubuntu, Helvetica, Arial; border-top: dotted 1px #868686; font-size: 1; margin: 0px auto; width: 100%;\">\n" +
"      </p>\n" +
"      \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          </tbody>\n" +
"        </table>\n" +
"        \n" +
"      </div>\n" +
"    \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"       <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td>\n" +
"           \n" +
"        \n" +
"      <div style=\"margin:0px auto;max-width:600px;\">\n" +
"        \n" +
"        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"          <tbody>\n" +
"            <tr>\n" +
"              <td style=\"direction:ltr;font-size:0px;padding:9px 0px 9px 0px;text-align:center;\">\n" +
"              \n" +
"            \n" +
"      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
"        \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"        \n" +
"            <tr>\n" +
"              <td align=\"center\" vertical-align=\"middle\" style=\"font-size:0px;padding:20px 20px 20px 20px;word-break:break-word;\">\n" +
"                \n" +
"      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
"        <tr>\n" +
"          <td align=\"center\" bgcolor=\"#ff99cc\" role=\"presentation\" style=\"border:none;border-radius:24px;cursor:auto;mso-padding-alt:9px 26px 9px 26px;background:#ff99cc;\" valign=\"middle\">\n" +
"            <a href=\"https://google.com\" style=\"display: inline-block; background: #ff99cc; color: #ffffff; font-family: Ubuntu, Helvetica, Arial, sans-serif, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: normal; line-height: 100%; margin: 0; text-decoration: none; text-transform: none; padding: 9px 26px 9px 26px; mso-padding-alt: 0px; border-radius: 24px;\" target=\"_blank\">\n" +
"              RATE US!\n" +
"            </a>\n" +
"          </td>\n" +
"        </tr>\n" +
"      </table>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          \n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"              </td>\n" +
"            </tr>\n" +
"          </tbody>\n" +
"        </table>\n" +
"        \n" +
"      </div>\n" +
"    \n" +
"     \n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    \n" +
"      </div>\n" +
"    \n" +
"      \n" +
"</body>\n" +
"    </html>";
//        
//        for (SaleLineItem lineItem : lineItems) {
//            quantity.concat("<div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial;\"><span style=\"color: #660066;\">" + lineItem.getQuantity() + "</span></p></div>\n");
//        }
//        
//        for (SaleLineItem lineItem : lineItems) {
//            productName.concat("<div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial;\"><span style=\"color: #660066;\">" + lineItem.getProd().getName() + "</span></p></div>\n");
//        }
//        
//        double subTotal = 0;
//        for (SaleLineItem lineItem : lineItems) {
//            productPrice.concat("<div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1.5;text-align:left;color:#660066;\"><p style=\"font-size: 11px; font-family: Ubuntu, Helvetica, Arial; text-align: right;\"><span style=\"color: #660066;\">R" + lineItem.getProd().getPrice() + "</span></p></div>\n");
////            subTotal += lineItem.getProduct().getPrice();
//        }
        
        return receipt;
    }
}

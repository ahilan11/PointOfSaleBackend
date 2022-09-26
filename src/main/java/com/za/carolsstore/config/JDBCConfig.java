package com.za.carolsstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JDBCConfig {

    private ObjectMapper om;
    private File configFile = new File("C:\\Users\\Vijay\\Documents\\NetBeansProjects\\CarolsStore\\src\\main\\java\\com\\za\\carolsstore\\config\\configFile.json");

    public JDBCConfig() {
        om = new ObjectMapper();
    }

    public String readURL() {
        JDBCConfig.URL url = null;
        try {
            url = om.readValue(configFile, JDBCConfig.URL.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return url.toString();
    }

    public String readPropertiesUser() {
        FileReader reader = null;
        Properties p = null;
        try {
            reader = new FileReader("C:\\Users\\Vijay\\Documents\\NetBeansProjects\\CarolsStore\\src\\main\\java\\com\\za\\carolsstore\\config\\UsernamePassword.properties");
            p = new Properties();
            p.load(reader);
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p.getProperty("db.user");
    }

    public String readPropertiesPassword() {
        FileReader reader = null;
        Properties p = null;
        try {
            reader = new FileReader("C:\\Users\\Vijay\\Documents\\NetBeansProjects\\CarolsStore\\src\\main\\java\\com\\za\\carolsstore\\config\\UsernamePassword.properties");
            p = new Properties();
            p.load(reader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(JDBCConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p.getProperty("db.password");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class URL {

        private String protocol;
        private String subProtocol;
        private String ip;
        private String port;
        private String resource;

        @Override
        public String toString() {
            return protocol + ":" + subProtocol + "://" + ip + ":" + port + "/" + resource;
        }
    }
}

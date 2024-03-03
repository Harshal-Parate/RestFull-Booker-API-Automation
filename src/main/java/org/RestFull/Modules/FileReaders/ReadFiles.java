package org.RestFull.Modules.FileReaders;

import java.io.FileReader;
import java.util.Properties;

public class ReadFiles {

    private static Properties properties;
    private static FileReader fileReader;

    static {
        properties = new Properties();
        try {
            fileReader = new FileReader("src/test/resources/Configuration/config.properties");
            properties.load(fileReader);
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }

    public static String getBaseUri(String Keyword) {
        if (Keyword.contains("Beta")) {
            return properties.getProperty("BetaBaseUri");
        } else if (Keyword.contains("Test")) {
            return properties.getProperty("TestBaseUri");
        } else if (Keyword.contains("Prod")) {
            return properties.getProperty("ProdBaseUri");
        } else {
            System.out.println("Invalid keyword: " + Keyword);
            return null;
        }
    }

    public static String getAuth(String auth) {

        try {
            properties = new Properties();
            fileReader = new FileReader("src/test/resources/Configuration/config.properties");
            properties.load(fileReader);
            return properties.getProperty(auth);
        } catch (Exception e) {
            System.out.println("File Error");
        }
        return null;
    }
}

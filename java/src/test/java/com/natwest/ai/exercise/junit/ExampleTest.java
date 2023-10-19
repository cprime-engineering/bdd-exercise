package com.natwest.bdd_exercise.test;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

 
class JUnit5ExampleTest {
 
    @Test
    void justAnExample() throws IOException {

        String client_id = "";
        String client_secret = "";

        try (InputStream input = new FileInputStream("src/test/java/com/natwest/ai/exercise/resources/properties/test.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            client_id = prop.getProperty("client_id");
            client_secret = prop.getProperty("client_secret");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        URL url = new URL("https://ob.sandbox.natwest.com/token");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept", "application/json");
        connection.setDoOutput(true);


        String queryString = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", client_id, client_secret);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = queryString.getBytes("utf-8");
            os.write(input, 0, input.length);			
            }

        InputStream responseStream = connection.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        AccessToken accessToken = mapper.readValue(responseStream, AccessToken.class);

        System.out.println(accessToken.access_token);

 
    }
}  
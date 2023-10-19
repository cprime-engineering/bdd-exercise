package com.natwest.bdd_exercise.test;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

 
class JUnit5ExampleTest {
 
    @Test
    void justAnExample() throws IOException {
        
        URL url = new URL("https://ob.sandbox.natwest.com/token");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept", "application/json");
        connection.setDoOutput(true);

        String queryString = "grant_type=client_credentials&client_id=Ruo55QIgQ3Ij7a3QFKGuJmt5i2V4j-sXCCg_FXZh7XI=&client_secret=spvZeXH7PA2GOJPx3fLL1XisLwTF2LcWD2Hwyz8ZSK0=";

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
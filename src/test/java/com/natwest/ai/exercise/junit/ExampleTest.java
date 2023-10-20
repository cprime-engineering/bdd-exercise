package com.natwest.ai.exercise.junit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.ai.exercise.authentication.AccessToken;
import com.natwest.ai.exercise.common.ApplicationProperties;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;

class JUnit5ExampleTest {

  @Test
  void justAnExample() throws IOException {

    ApplicationProperties application_properties = ApplicationProperties.getInstance();

    URL url = new URL("https://ob.sandbox.natwest.com/token");

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("accept", "application/json");
    connection.setDoOutput(true);

    String queryString =
        String.format(
            "grant_type=client_credentials&client_id=%s&client_secret=%s",
            application_properties.getClientID(), application_properties.getClientSecret());

    try (OutputStream os = connection.getOutputStream()) {
      byte[] input = queryString.getBytes("utf-8");
      os.write(input, 0, input.length);
    }

    InputStream responseStream = connection.getInputStream();

    ObjectMapper mapper = new ObjectMapper();

    AccessToken accessToken = mapper.readValue(responseStream, AccessToken.class);

    System.out.println(accessToken.access_token);
  }
}

package com.natwest.ai.exercise.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

  private static final String PROPERTIES_PATH =
      "src/main/resources/properties/com/natwest/ai/exercise/application.properties";

  private String client_id;

  public String getClientID() {
    return client_id;
  }

  private String client_secret;

  public String getClientSecret() {
    return client_secret;
  }

  private static ApplicationProperties application_properties;

  public static ApplicationProperties getInstance() {

    if (application_properties == null) {
      application_properties = new ApplicationProperties();
    }
    return application_properties;
  }

  private ApplicationProperties() {

    try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {

      Properties prop = new Properties();

      prop.load(input);

      client_id = prop.getProperty("client_id");
      client_secret = prop.getProperty("client_secret");

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

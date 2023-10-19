package com.natwest.bdd_exercise.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
    public final String access_token;
    public final String token_type;
    public final String expires_in;

    public AccessToken(@JsonProperty("access_token") String access_token,
                @JsonProperty("token_type") String token_type,
                @JsonProperty("expires_in") String expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }
}
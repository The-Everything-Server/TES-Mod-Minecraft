package com.thesaltynewfie.tesmod.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty("api_token")
    private String apiToken;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}

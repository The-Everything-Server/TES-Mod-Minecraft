package com.thesaltynewfie.tesmod.network.Types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
    private int id;
    private String username;
    @JsonProperty("discord_id")
    private String discordId;
    private int currency;
    private float experience;
    @JsonProperty("mc_username")
    private String mcUsername;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public String getMcUsername() {
        return mcUsername;
    }

    public void setMcUsername(String mcUsername) {
        this.mcUsername = mcUsername;
    }
}

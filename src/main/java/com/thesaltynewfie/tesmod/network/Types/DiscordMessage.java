package com.thesaltynewfie.tesmod.network.Types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordMessage {
    private String message;

    @JsonProperty("channel_id")
    private String channelId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}

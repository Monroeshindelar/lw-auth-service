package com.mshindelar.accountservice.api.discord;

import com.mshindelar.accountservice.api.ApiBinding;
import org.springframework.http.ResponseEntity;

public class DiscordApiBinding extends ApiBinding {

    private static final String API_BASE_URL = "https://discord.com/api/users/@me";

    public DiscordApiBinding(String accessToken) {
        super(accessToken);
    }

    public String getGuilds() {
        ResponseEntity<String> resp = restTemplate.getForEntity(API_BASE_URL + "/guilds", String.class);
        return resp.toString();
    }

    public String getConnections() {
        ResponseEntity<String> resp = restTemplate.getForEntity(API_BASE_URL + "/connections", String.class);
        return resp.toString();
    }
}

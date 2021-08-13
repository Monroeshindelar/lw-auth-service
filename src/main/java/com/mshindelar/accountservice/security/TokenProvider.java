package com.mshindelar.accountservice.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class TokenProvider {
    private static final String key = "926D96C90030DD58429D2751AC1BDBBC";
    private static final long expireMs = 864000000;

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public String createToken(Authentication authentication) {
        String principalId = (String) ((DefaultOAuth2User) authentication.getPrincipal()).getAttributes().get("id");

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName());

        JSONObject token = new JSONObject();
        token.appendField("access_token", authorizedClient.getAccessToken().getTokenValue());
        //token.appendField("refresh_token", authorizedClient.getAccessToken().getTokenValue());
        token.appendField("id", principalId);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireMs);

        return Jwts.builder()
                .setSubject(token.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(claims.getSubject());

        return (String) map.get("id");
        //return Long.parseLong((String) map.get("id"));
    }
}
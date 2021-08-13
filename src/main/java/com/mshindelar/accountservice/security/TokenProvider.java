package com.mshindelar.accountservice.security;

import com.mshindelar.accountservice.config.TempConfiguration;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class TokenProvider {

    //private static String key;

    @Autowired
    private TempConfiguration jwtConfig;

    private static final long expireMs = 864000000;

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    public String createToken(Authentication authentication) {
        String principalId = (String) ((DefaultOAuth2User) authentication.getPrincipal()).getAttributes().get("id");

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        JSONObject token = new JSONObject();
        token.appendField("id", principalId);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireMs);

        return Jwts.builder()
                .setSubject(token.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getKey())
                .compact();
    }

    public boolean validateToken(String authToken) {
        String key = jwtConfig.getKey();
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
        String key = jwtConfig.getKey();
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
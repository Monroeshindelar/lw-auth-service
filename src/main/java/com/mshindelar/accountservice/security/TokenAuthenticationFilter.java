//package com.mshindelar.accountservice.security;
//
//import com.mshindelar.accountservice.auth.DiscordOAuth2UserService;
//import com.mshindelar.accountservice.entity.Account;
//import com.mshindelar.accountservice.service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
////    @Autowired
////    private DiscordOAuth2UserService userService;
//
//    @Autowired
//    private CustomUserDetailService userDetailService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String jwt = this.getJwtFromRequest(request);
//            if(StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
//                String id = this.tokenProvider.getUserIdFromToken(jwt);
//                UserDetails userDetails = this.userDetailService.loadUserByPrincipalId(id);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            }
//
//        } catch (Exception ex) {
//            logger.error("ERROR.");
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//}

package com.codecool.homee_backend.config.auth;

import com.codecool.homee_backend.service.auth.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(BEARER)) {
            String jwtToken = authHeader.substring(BEARER.length() + 1);
            try {
                String username = jwtTokenService.getUsernameFromToken(jwtToken);
                authenticateUser(request, jwtToken, username);
            } catch (IllegalArgumentException e) {
                log.error("Unable to parse JWT token", e);
            } catch (ExpiredJwtException e) {
                log.warn("JWT token {} is expired", jwtToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request, String jwtToken, String username) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenService.validateToken(jwtToken, userDetails)) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
}

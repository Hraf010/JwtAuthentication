package com.hraf.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String jwt = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println(jwt);
        if(jwt == null ||!jwt.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
                .getBody();
        String userName = claims.getSubject();
        ArrayList<Map<String,String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(r -> {
            ((ArrayList<GrantedAuthority>) authorities).add(new SimpleGrantedAuthority(r.get("authority")));
        });
        //charger l'utilisateur athentifié : f7ala katgol l spring bli khona li sift requette hawa identité dyalo
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);



    }
}

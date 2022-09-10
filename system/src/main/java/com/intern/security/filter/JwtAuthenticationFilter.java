package com.intern.security.filter;


import com.intern.logging.annotation.NoLogging;
import com.intern.security.service.JwtService;
import com.intern.security.service.impl.UserDetailServiceImpl;
import com.intern.tools.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * This class is used to filter login by jwt token
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @NoLogging
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader!=null){
            String accessToken=authHeader.replace("Bearer ","");
            String username= jwtService.getUsername(accessToken);
            if(username!=null&&redisUtil.getToken(username).equals(accessToken)) {
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}

package com.intern.security.handler;

import com.intern.common.dao.UserProfile;
import com.intern.logging.annotation.NoLogging;
import com.intern.tools.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    RedisUtil redisUtil;
    @Override
    @NoLogging
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication!=null) {
            UserProfile user = (UserProfile) authentication.getPrincipal();
            String username = user.getUsername();
            redisUtil.deleteToken(username);
            System.out.println("delete");
        }else{
            throw new BadCredentialsException("Token isn't exist.");
        }
    }
}

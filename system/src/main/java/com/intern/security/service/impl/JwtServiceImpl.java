package com.intern.security.service.impl;


import com.intern.common.mapper.UserProfileMapper;
import com.intern.security.service.JwtService;
import com.intern.tools.utils.RedisUtil;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.intern.common.variable.SystemVariable.EXPIRATION_TIME;
import static com.intern.common.variable.SystemVariable.SECRET_KEY;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String authority = userProfileMapper.getAuthority(userDetails.getUsername());
        String username = userDetails.getUsername();
        claims.put("username", username);

        String token= Jwts.builder()
                .setClaims(claims)
                .setAudience(authority)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        redisUtil.setToken(username,token);
        return token;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getClaimsFromToken(token).getSubject();
         if (username.equals(user.getUsername()) && !isTokenExpire(token)){
             return redisUtil.getToken(username).equals(token);
         }
         return false;
    }


    @Override
    public Boolean isTokenExpire(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

    }

    public String getUsername(String token) {
        Claims claims = getClaimsFromToken(token);

        return String.valueOf(claims.get("username"));

    }


    public Map<String, Object> parseToken(String token) {
        Claims claims = getClaimsFromToken(token);

        return claims.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}


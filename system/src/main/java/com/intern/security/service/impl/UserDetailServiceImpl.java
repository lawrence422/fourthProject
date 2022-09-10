package com.intern.security.service.impl;


import com.intern.common.dao.UserProfile;
import com.intern.common.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserProfileMapper userProfileMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userProfileMapper.checkUsernameExist(username) == 0) {
            throw new UsernameNotFoundException("The user isn't exist.");
        } else {
            String password = userProfileMapper.getPassword(username);
            String authority = userProfileMapper.getAuthority(username);
            if ("admin".equals(authority)) {
                return new UserProfile(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            } else {
                return new UserProfile(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("normal"));
            }

        }

    }
}


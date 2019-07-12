package com.hraf.service;

import com.hraf.entities.AppRole;
import com.hraf.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser appUser = accountService.findUserByUsername(s);
        if (appUser == null)
            throw  new UsernameNotFoundException(s);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(AppRole role : appUser.getRoles())
            authorities.add(new SimpleGrantedAuthority(role.getRole()));


        System.out.println(appUser.getPassword());
        return new User(appUser.getUsername() , appUser.getPassword() , authorities);
    }
}

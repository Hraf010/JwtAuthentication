package com.hraf.service;

import com.hraf.dao.AppRoleRepository;
import com.hraf.dao.AppUserRepository;
import com.hraf.entities.AppRole;
import com.hraf.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPass);
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String role) {
       AppUser appUser = appUserRepository.findByUsername(username);
       AppRole appRole = appRoleRepository.findByRole(role);
       appUser.getRoles().add(appRole);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}

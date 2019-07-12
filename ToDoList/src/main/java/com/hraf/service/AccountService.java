package com.hraf.service;

import com.hraf.entities.AppRole;
import com.hraf.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username , String role);
    public AppUser findUserByUsername(String username);
}

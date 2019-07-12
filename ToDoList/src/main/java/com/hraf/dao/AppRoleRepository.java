package com.hraf.dao;

import com.hraf.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    public AppRole findByRole(String role);
}

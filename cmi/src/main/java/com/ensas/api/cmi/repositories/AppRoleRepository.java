package com.ensas.api.cmi.repositories;

import com.ensas.api.cmi.entities.AppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends MongoRepository<AppRole,String> {
    public AppRole findByRoleName(String roleName);
}

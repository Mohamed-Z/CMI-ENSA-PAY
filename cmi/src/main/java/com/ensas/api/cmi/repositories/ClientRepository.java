package com.ensas.api.cmi.repositories;

import com.ensas.api.cmi.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client,String> {
    public Client findByNumTel(String numTel);
    public List<Client> findAllByAgentID(String agentID);
}

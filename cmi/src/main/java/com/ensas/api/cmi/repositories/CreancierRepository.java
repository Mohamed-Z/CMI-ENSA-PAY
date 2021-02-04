package com.ensas.api.cmi.repositories;

import com.ensas.api.cmi.entities.Creancier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreancierRepository extends MongoRepository<Creancier,String> {
    public Creancier findByUrl(String url);
}

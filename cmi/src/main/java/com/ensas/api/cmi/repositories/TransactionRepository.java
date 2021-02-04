package com.ensas.api.cmi.repositories;

import com.ensas.api.cmi.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository  extends MongoRepository<Transaction,String> {
    public List<Transaction> findAllByEnPar(String enPar);
}

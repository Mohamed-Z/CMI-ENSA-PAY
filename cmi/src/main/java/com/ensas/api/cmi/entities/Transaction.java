package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Transaction {

    @Id
    private String id;
    private String enPar;
    private String rePar;
    private Double montant;
    private Date date;
}

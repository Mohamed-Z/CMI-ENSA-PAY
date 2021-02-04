package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Creance {

    @Id
    private String code;
    private String nom;
}

package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document @Data @AllArgsConstructor @NoArgsConstructor
public class Creancier {

    @Id
    private String code;
    private String nom;
    private String categorie;
    private String url;

    //@DBRef
    private Collection<Creance> creances = new ArrayList<>();

    @Override
    public String toString() {
        return "Creancier{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}

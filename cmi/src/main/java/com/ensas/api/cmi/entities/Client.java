package com.ensas.api.cmi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

@Document
@Data @AllArgsConstructor @NoArgsConstructor
@XmlRootElement(name="client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    @XmlTransient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean actived;
    private Double solde;
    private String agentID;

    @DBRef
    private Collection<AppRole> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", numTel='" + numTel + '\'' +
                ", password='" + password + '\'' +
                ", actived=" + actived +
                ", solde=" + solde +
                '}';
    }
}

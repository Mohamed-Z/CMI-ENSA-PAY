package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@XmlRootElement(name="role")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppRole {
    @Id
    private String id;
    private String roleName;
}

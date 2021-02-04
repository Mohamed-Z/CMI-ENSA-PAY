package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ResultImpaye {

    private List<Impaye> impayeList;
    private Creancier creancier;
}

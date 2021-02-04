package com.ensas.api.cmi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Impaye {

    private String code;
    private Double amount;
    private String title;
    private String crCode;
}

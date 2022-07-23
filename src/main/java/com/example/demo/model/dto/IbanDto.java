package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IbanDto {
    private Long id;
    private String iban;

    public IbanDto() {}
}

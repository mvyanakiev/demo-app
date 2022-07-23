package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ClientDto {
    private Long id;
    private String name;
    private List<IbanDto> ibanList = new ArrayList<>();

    public ClientDto() {}
}

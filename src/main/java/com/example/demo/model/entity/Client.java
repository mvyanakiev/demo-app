package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clinets")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Iban> ibanList;
}

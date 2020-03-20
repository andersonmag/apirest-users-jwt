package com.example.apirestful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.lang.Nullable;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Id do Contato")
    private Long id;
    @ApiModelProperty(value = "Nome do Contato")
    private String name;
    @ApiModelProperty(value = "Número do Contato")
    @Nullable
    @Column(unique = true)
    private String number;

    public Contact() {}

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
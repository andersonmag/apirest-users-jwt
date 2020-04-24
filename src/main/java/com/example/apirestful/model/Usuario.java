package com.example.apirestful.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.lang.Nullable;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Id do Usuario")
    private Long id;

    @ApiModelProperty(value = "Nome do Usuario")
    private String nome;

    @ApiModelProperty(value = "Email do Usuario")
    @Nullable
    private String email;
    
    @Nullable
    private String senha;

    @ApiModelProperty(value = "Lista de Notas do Usuario")
    @OneToMany(mappedBy = "usuario", orphanRemoval = true,
     cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Nota> notas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
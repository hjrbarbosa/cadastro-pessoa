package com.kumulus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "pessoa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;


    @OneToMany(mappedBy="pessoa", orphanRemoval = true)
    private Set<Endereco> enderecos;


    public void addEndereco(Endereco e) {
        if (this.enderecos == null) {
            this.enderecos = new HashSet<>();
        }
        this.enderecos.add(e);
    }


}

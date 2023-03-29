package com.kumulus.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pessoa")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_pessoa")
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "sexo", length = 2, nullable = false)
    private String sexo;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @OneToMany(mappedBy="pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos;

    public void addEndereco(Endereco e) {
        if (this.enderecos == null) {
            this.enderecos = new HashSet<>();
        }
        this.enderecos.add(e);
    }

}

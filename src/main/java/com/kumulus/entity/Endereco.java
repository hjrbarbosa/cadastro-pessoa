package com.kumulus.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Endereco {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;

        @Column(name = "estado", length = 2, nullable = false)
        private String uf;

        @Column(name = "cidade", length = 100, nullable = false)
        private String cidade;

        @Column(name = "logradouro", length = 100, nullable = false)
        private String logradouro;

        @Column(name = "numero", nullable = false)
        private Integer numero;

        @Column(name = "cep", length = 8, nullable = false)
        private String cep;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="cod_pessoa", nullable=false)
        private Pessoa pessoa;

}

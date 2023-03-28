package com.kumulus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;

        @Column(name = "estado", length = 2, nullable = false)
        private String uf;

        @Column(name = "cidade", length = 100, nullable = false)
        private String cidade;

        @ManyToOne
        @JoinColumn(name="pessoa_id", nullable=false)
        private Pessoa pessoa;

}

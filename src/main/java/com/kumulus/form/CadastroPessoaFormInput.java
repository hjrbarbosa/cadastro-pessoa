package com.kumulus.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPessoaFormInput {

    private Long id;

    private String nome;

    private Date dataNascimento;

    private String sexo;

    private String estado;

    private String cidade;

    private String logradouro;

    private Integer numero;

    private String cep;
}

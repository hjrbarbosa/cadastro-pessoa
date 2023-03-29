package com.kumulus.form;

import lombok.*;

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
}

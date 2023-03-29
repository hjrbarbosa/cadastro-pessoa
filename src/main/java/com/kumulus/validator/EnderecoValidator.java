package com.kumulus.validator;

import com.kumulus.entity.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoValidator {

    public void validaEndereco(List<Endereco> enderecos){
        if (enderecos.size() == 0){
            throw new IllegalArgumentException("Prenchimento do campo endereço é obrigatório.");
        }
        enderecos.forEach(e -> enderecoVazio(e));

    }

    public void enderecoVazio(Endereco endereco) {
        if (endereco.getUf().isEmpty() || endereco.getCidade().isEmpty()){
            throw new IllegalArgumentException("Prenchimento do campo endereço é obrigatório.");
        }
        if (endereco.getUf().length() != 2){
            throw new IllegalArgumentException("Estado deve possuir 2 caracteres.");
        }
    }
}

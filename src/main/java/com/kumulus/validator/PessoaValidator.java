package com.kumulus.validator;

import com.kumulus.entity.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaValidator {

    public void beforeSave(Pessoa p) {
//        if (p.getEnderecos() == null || p.getEnderecos().isEmpty()) {
//            throw new IllegalArgumentException("Prenchimento do campo endereço é obrigatório.");
//        }
    }

}

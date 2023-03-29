package com.kumulus.validator;

import com.kumulus.entity.Endereco;
import com.kumulus.form.CadastroPessoaFormInput;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoValidator {

//    public void beforeSave(Pessoa p) {
//        if (p.getEnderecos() == null || p.getEnderecos().isEmpty()) {
//            throw new IllegalArgumentException("Prenchimento do campo endereço é obrigatório.");
//        }
//    }

    public void validaEndereco(List<Endereco> enderecos){
        if (enderecos.size() == 0){
            throw new IllegalArgumentException("Prenchimento do campo endereço é obrigatório.");
        }

    }

}

package com.kumulus.service;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
import com.kumulus.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnderecoService {

    @Resource
    private EnderecoRepository enderecoRepository;

//    @Resource
//    private PessoaValidator pessoaValidator;

    public void salvar(Pessoa heldinho, Endereco e) {
        e.setPessoa(heldinho);
        this.enderecoRepository.save(e);
    }

    public List<Endereco> findAll() {
        return this.enderecoRepository.findAll();
    }

    public void salvarEnderecos(List<Endereco> enderecos) {
        enderecos.forEach(e -> this.enderecoRepository.save(e));
    }

//    public List<Pessoa> findAll() {
//        return this.pessoaRepository.findAll();
//    }
}

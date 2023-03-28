package com.kumulus.service;

import com.kumulus.entity.Pessoa;
import com.kumulus.repository.PessoaRepository;
import com.kumulus.validator.PessoaValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PessoaService {

    @Resource
    private PessoaRepository pessoaRepository;

    @Resource
    private PessoaValidator pessoaValidator;

    public void salvar(Pessoa p) {
        this.pessoaValidator.beforeSave(p);
        this.pessoaRepository.save(p);
    }

    public List<Pessoa> findAll() {
        return this.pessoaRepository.findAll();
    }
}

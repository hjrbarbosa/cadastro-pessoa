package com.kumulus.service;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
import com.kumulus.form.CadastroPessoaFormInput;
import com.kumulus.repository.PessoaRepository;
import com.kumulus.validator.EnderecoValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PessoaService {

    @Resource
    private PessoaRepository pessoaRepository;

    @Resource
    private EnderecoValidator enderecoValidator;

    @Resource
    private EnderecoService enderecoService;

    public void salvar(Pessoa p) {
//        this.pessoaValidator.beforeSave(p);
        this.pessoaRepository.save(p);
    }

    public List<Pessoa> findAll() {
        return this.pessoaRepository.findAll();
    }

    public void salvarFromInput(CadastroPessoaFormInput input, List<Endereco> enderecos) {
        enderecoValidator.validaEndereco(enderecos);
        Pessoa p = new Pessoa();
        p.setNome(input.getNome());
        p.setDataNascimento(input.getDataNascimento());
        p.setSexo(input.getSexo());
        this.pessoaRepository.save(p);

        enderecos.forEach(e -> e.setPessoa(p));
        this.enderecoService.salvarEnderecos(enderecos);


    }

    public void delete(Pessoa selectedPessoa) {
        if (selectedPessoa == null){
            throw new EntityNotFoundException("Pessoa nao encontrada.");
        }
        this.pessoaRepository.deleteById(selectedPessoa.getId());
    }
}

package com.kumulus.service;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
import com.kumulus.form.CadastroPessoaFormInput;
import com.kumulus.repository.PessoaRepository;
import com.kumulus.validator.EnderecoValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        p.setEnderecos(getEnderecos(enderecos));
        this.pessoaRepository.save(p);

//        enderecos.forEach(e -> e.setPessoa(p));
//        this.enderecoService.salvarEnderecos(enderecos);


    }

    private Set<Endereco> getEnderecos(List<Endereco> enderecos) {
        Set<Endereco> enderecoSet = new HashSet<>();
        enderecos.forEach(enderecoSet::add);
        return enderecoSet;
    }

    public void delete(Pessoa selectedPessoa) {
        if (selectedPessoa == null){
            throw new EntityNotFoundException("Pessoa nao encontrada.");
        }
        this.pessoaRepository.deleteById(selectedPessoa.getId());
    }

    public void atualizarFromInput(CadastroPessoaFormInput input, List<Endereco> enderecos) {
        Pessoa pessoaUpdate = this.pessoaRepository.findById(input.getId()).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado cadastro da pessoa."));
        if (!pessoaUpdate.getNome().equals(input.getNome())){
            pessoaUpdate.setNome(input.getNome());
        }
        if(!(pessoaUpdate.getDataNascimento().compareTo(input.getDataNascimento()) == 0)){
            pessoaUpdate.setDataNascimento(input.getDataNascimento());
        }
        if (!pessoaUpdate.getSexo().equals(input.getSexo())){
            pessoaUpdate.setSexo(input.getSexo());
        }

        pessoaUpdate.getEnderecos().removeIf(endereco -> !enderecos.stream()
                .map(Endereco::getId)
                .collect(Collectors.toList())
                .contains(endereco.getId()));

        enderecos.stream().filter(endereco -> endereco.getId() == null)
                .forEach(enderecoUpdateInput -> {
                    Endereco enderecoNovo = new Endereco();
                    enderecoNovo.setPessoa(pessoaUpdate);
                    pessoaUpdate.addEndereco(enderecoNovo);
                });
        this.pessoaRepository.save(pessoaUpdate);

    }
}

package com.kumulus.bean;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
import com.kumulus.form.CadastroPessoaFormInput;
import com.kumulus.service.PessoaService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Named
@ViewScoped
@Getter
@Setter
public class CadastroPessoaBean implements Serializable {


    private List<Pessoa> pessoas;

    private CadastroPessoaFormInput pessoaFormInput;

    private Pessoa pessoa;

    private Endereco selectedEndereco;

    private List<Endereco> selectedEnderecos;

    @Resource
    private PessoaService pessoaService;

    @PostConstruct
    public void init() {
        this.pessoas = this.pessoaService.findAll();
    }

    public void novoFormPessoa() {
        this.pessoaFormInput = new CadastroPessoaFormInput();
        this.selectedEnderecos = null;
    }

    public void salvar() {
        try {
            if (this.pessoaFormInput.getId() == null){
                this.pessoaService.salvarFromInput(this.pessoaFormInput, selectedEnderecos);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso."));
            } else {
                this.pessoaService.atualizarFromInput(this.pessoaFormInput, selectedEnderecos);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro atualizado com sucesso."));
            }
            this.pessoas = this.pessoaService.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        PrimeFaces.current().executeScript("PF('manageCadastroPessoaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pessoas");
    }

    public void adicionaEndereco() {
        if(this.selectedEnderecos == null){
            this.selectedEnderecos = new ArrayList<>();
        }
        this.pessoaService.validaEnderecoPessoa(this.pessoaFormInput);
        this.selectedEnderecos.add(getEnderecoFromInput(this.pessoaFormInput));
        limpaCamposCidade(this.pessoaFormInput);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Endereço adicionado com sucesso."));
    }

    public void deletePessoa(){
        this.pessoaService.delete(this.pessoa);
        this.pessoas.remove(this.pessoa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pessoa Removida"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pessoas");
    }

    public void atualizaForm(){
        CadastroPessoaFormInput input = CadastroPessoaFormInput.builder()
                .id(this.pessoa.getId())
                .nome(this.pessoa.getNome())
                .dataNascimento(this.pessoa.getDataNascimento())
                .sexo(this.pessoa.getSexo())
                .build();
        if (this.selectedEndereco == null){
            this.selectedEnderecos = new ArrayList<>();
        }
        this.pessoa.getEnderecos().forEach(e -> this.selectedEnderecos.add(e));
        this.pessoaFormInput = input;
        PrimeFaces.current().executeScript("PF('updatePessoaDialog').hide()");
    }

    private void limpaCamposCidade(CadastroPessoaFormInput input) {
        input.setEstado(null);
        input.setCidade(null);
        input.setLogradouro(null);
        input.setNumero(null);
        input.setCep(null);
    }

    private Endereco getEnderecoFromInput(CadastroPessoaFormInput input) {
        return Endereco.builder()
                .uf(input.getEstado())
                .cidade(input.getCidade())
                .logradouro(input.getLogradouro())
                .numero(input.getNumero())
                .cep(input.getCep())
                .build();
    }

}

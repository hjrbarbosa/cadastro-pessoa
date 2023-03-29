package com.kumulus.bean;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
import com.kumulus.form.CadastroPessoaFormInput;
import com.kumulus.service.EnderecoService;
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
import java.util.*;

@Component
@Named
@ViewScoped
@Getter
@Setter
public class CadastroPessoaBean implements Serializable {


    private List<Pessoa> pessoas;
    private List<Endereco> enderecos;

    private CadastroPessoaFormInput selecionarCadastroPessoaFormInput;
    private Pessoa selectedPessoa;
    private Endereco selectedEndereco;

    private List<Pessoa> selectedPessoas;
    private List<Endereco> selectedEnderecos;

    @Resource
    private PessoaService pessoaService;

    @Resource
    private EnderecoService enderecoService;

    @PostConstruct
    public void init() {
        Pessoa heldinho = Pessoa
                .builder()
                .dataNascimento(new Date())
                .sexo("MA")
                .nome("Heldinho")
                .build();

        Endereco endereco = Endereco
                .builder()
                .uf("PA")
                .cidade("Belém")
                .pessoa(heldinho)
                .build();

        heldinho.addEndereco(endereco);
//        heldinho.setEnderecos(getEndereco(heldinho));

        this.pessoaService.salvar(heldinho);
//        this.enderecoService.salvar(heldinho, endereco);


        this.pessoas = this.pessoaService.findAll();
    }

    public void openNew() {
        this.selecionarCadastroPessoaFormInput = new CadastroPessoaFormInput();
        this.selectedEnderecos = null;
    }

    public void enderecoNew() {
        this.selectedEndereco = new Endereco();
    }

    public void salvar() {
        try {
            this.pessoaService.salvarFromInput(this.selecionarCadastroPessoaFormInput, selectedEnderecos);

            this.pessoas = this.pessoaService.findAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso."));
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

        this.selectedEnderecos.add(getEnderecoFromInput(this.selecionarCadastroPessoaFormInput));
        limpaCamposCidade(this.selecionarCadastroPessoaFormInput);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Endereço adicionado com sucesso."));
    }

    private void limpaCamposCidade(CadastroPessoaFormInput input) {
        input.setEstado(null);
        input.setCidade(null);
    }

    private Endereco getEnderecoFromInput(CadastroPessoaFormInput input) {
        return Endereco.builder()
                .uf(input.getEstado())
                .cidade(input.getCidade())
                .build();
    }

    public void deletar() {
        this.pessoas.remove(this.selectedPessoa);
        this.selectedPessoas.remove(this.selectedPessoa);
        this.selectedPessoa = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelected()) {
            int size = this.selectedPessoas.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public void deletePessoa(){
        this.pessoaService.delete(this.selectedPessoa);
        this.pessoas.remove(this.selectedPessoa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pessoa Removida"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pessoas");
    }

    public void pessoaUpdate(){
        CadastroPessoaFormInput a = this.selecionarCadastroPessoaFormInput;
        Pessoa p = this.selectedPessoa;
    }

    public boolean hasSelected() {
        return this.selectedPessoas != null && !this.selectedPessoas.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.pessoas.removeAll(this.selectedPessoas);
        this.selectedPessoas = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pessoa excluída com sucesso!"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void atualizaForm(){
        Pessoa a = this.selectedPessoa;
        CadastroPessoaFormInput input = CadastroPessoaFormInput
                .builder().build();

    }

}

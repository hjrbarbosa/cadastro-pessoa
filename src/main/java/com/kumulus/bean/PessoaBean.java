package com.kumulus.bean;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
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
public class PessoaBean implements Serializable {


    private List<Pessoa> pessoas;

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
        Endereco endereco = Endereco
                .builder()
                .uf("PA")
                .cidade("Belém")
                .build();

        Pessoa heldinho = Pessoa
                .builder()
                .dataNascimento(new Date())
                .sexo("MA")
                .nome("Heldinho")
                .build();

//        heldinho.addEndereco(endereco);
//        heldinho.setEnderecos(getEndereco(heldinho));

        this.pessoaService.salvar(heldinho);
        this.enderecoService.salvar(heldinho, endereco);


        this.pessoas = this.pessoaService.findAll();
    }

    private Set<Endereco> getEndereco(Pessoa heldinho) {
        Set<Endereco> enderecoSet = new HashSet<>();
        Endereco endereco = Endereco
                .builder()
                .uf("PA")
                .cidade("Belém")
                .pessoa(heldinho)
                .build();
        enderecoSet.add(endereco);
        return enderecoSet;
    }

    public void openNew() {
        this.selectedPessoa = new Pessoa();
    }

    public void enderecoNew() {
        this.selectedEndereco = new Endereco();
    }

    public void salvar() {
//        try {
//            this.selectedPessoa.addEndereco(
//                    Endereco
//                            .builder()
//                            .uf("CE")
//                            .cidade("Belém")
//                    .build()
//            );
            this.pessoaService.salvar(this.selectedPessoa);

            this.pessoas = this.pessoaService.findAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso."));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
//        }
        PrimeFaces.current().executeScript("PF('managePessoaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pessoas");
    }

    public void adicionaEndereco() {
        if(this.selectedEnderecos == null){
            this.selectedEnderecos = new ArrayList<>();
        }
        this.selectedEnderecos.add(this.selectedEndereco);

        this.selectedEndereco = new Endereco();

//        PrimeFaces.current().executeScript("PF('manageEnderecoDialog').hide()");
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


}

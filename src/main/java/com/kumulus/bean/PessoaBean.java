package com.kumulus.bean;

import com.kumulus.entity.Endereco;
import com.kumulus.entity.Pessoa;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@Named
@ViewScoped
@Getter
@Setter
public class PessoaBean implements Serializable {


    private List<Pessoa> pessoas;

    private Pessoa selectedPessoa;

    private List<Pessoa> selectedPessoas;

    @Resource
    private PessoaService pessoaService;

    @PostConstruct
    public void init() {
        Endereco endereco = Endereco
                .builder()
                .uf("CE")
                .cidade("Belém")
                .build();

        Pessoa heldinho = Pessoa
                .builder()
                .dataNascimento(new Date())
                .nome("Heldinho")
                .build();

        heldinho.addEndereco(endereco);

        this.pessoaService.salvar(heldinho);


        this.pessoas = this.pessoaService.findAll();
    }

    public void openNew() {
        this.selectedPessoa = new Pessoa();
    }

    public void salvar() {
        try {
            this.selectedPessoa.addEndereco(
                    Endereco
                            .builder()
                            .uf("CE")
                            .cidade("Belém")
                    .build()
            );
            this.pessoaService.salvar(this.selectedPessoa);

            this.pessoas = this.pessoaService.findAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        PrimeFaces.current().executeScript("PF('managePessoaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pessoas");
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

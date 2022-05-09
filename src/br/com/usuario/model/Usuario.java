package br.com.usuario.model;

import java.util.Date;

public class Usuario {
    private int id; //                 <-- ID do usuário (Primary Key)
    private String nome; //            <-- Nome do usuário
    private int idade; //              <-- Idade do usuário
    private Date dataCadastro; //      <-- Data de cadastro do usuário no sistema

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}

package Medidas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rmsouza
 */
public class Funcionario {
    private Integer id;
    private String nome;
    private String email;
    private String cnpj;
    private String senha;

//    public Funcionario(Integer id, String nome, String email, String cnpj, String senha) {
//        this.id = id;
//        this.nome = nome;
//        this.email = email;
//        this.cnpj = cnpj;
//        this.senha = senha;
//    }

    public void getCheck(){
        
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("\nEMAIL: %s\nSENHA: %s\n",
                email, senha);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medidas;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmsouza
 */
public class Hospital {
//CONEXAO BANCO
        ConexaoTechHealth connection = new ConexaoTechHealth();
        JdbcTemplate banco = connection.getConexao();

        //vm
        ConexaoDocker connection2 = new ConexaoDocker();
        JdbcTemplate bancoVM = connection2.getConexao();
        
        
    private Integer idHospital;
    private String cnpj;
    private String nome;
    private String bairro;
    private String cidade;
    private String endereco;
    private String uf;

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public String getCnpj() {
        List<Hospital> listaNome = banco.query("select cnpj from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String cnpjNovo = listaNome.get(0).toString();
        return cnpjNovo;
    }

    public String getNome() {
        List<Hospital> listaNome = banco.query("select nome_Hospital from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String nomeNovo = listaNome.get(0).toString();
        return nomeNovo;
    }

    public String getBairro() {
        List<Hospital> listaNome = banco.query("select bairro from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String bairroNovo = listaNome.get(0).toString();
        return bairroNovo;
    }

    public String getCidade() {
        List<Hospital> listaNome = banco.query("select cidade from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String cidadeNova = listaNome.get(0).toString();
        return cidadeNova;
    }

    public String getUf() {
        List<Hospital> listaNome = banco.query("select uf from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String ufNovo = listaNome.get(0).toString();
        return ufNovo;
    }

    public String getEndereco() {
        List<Hospital> listaNome = banco.query("select endereco from hospital where idHospital = 1", new BeanPropertyRowMapper(Hospital.class));
        String enderecoNvo = listaNome.get(0).toString();
        return enderecoNvo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        //StringBuilder stb = new StringBuilder();

        sb.append("\nID do Hospital: ")
                .append(getIdHospital())
                .append("\n");

        sb.append("\nNome do Hospital: ")
                .append(getNome())
                .append("\n");

        sb.append("CNPJ: ")
                .append(getCnpj())
                .append("\n");
        sb.append("Endereço: ")
                .append(getEndereco())
                .append("\n");

        sb.append("Cidade: ")
                .append(getCidade())
                .append("\n");

        sb.append("Bairro: ")
                .append(getBairro())
                .append("\n");

        sb.append("UF ")
                .append(getUf())
                .append("\n");

        return sb.toString();
    }

}

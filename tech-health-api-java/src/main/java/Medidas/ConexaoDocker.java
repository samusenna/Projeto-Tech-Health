package Medidas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmsouza
 */
public class ConexaoDocker {

    private JdbcTemplate conexao2;

    public ConexaoDocker() {

        BasicDataSource datasource = new BasicDataSource();

//        //conexao docker
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");;
        datasource.setUrl("jdbc:mysql://localhost:3306/grupo9?autoReconnect=true&useSSL=false");
        datasource.setUsername("root");
        datasource.setPassword("urubu100");

        //        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        datasource.setUrl("jdbc:mysql://localhost:3306/grupo9?useTimezone=true&serverTimezone=UTC"); //grupo9 = meu database;
//
//        datasource.setUsername("root");
//
//        datasource.setPassword("jujuba123");
        conexao2 = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConexao() {
        return conexao2;
    }
}

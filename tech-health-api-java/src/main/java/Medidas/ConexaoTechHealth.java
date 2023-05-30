/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medidas;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmsouza
 */
public class ConexaoTechHealth {

    private JdbcTemplate conexao;

    public ConexaoTechHealth() {

        BasicDataSource datasource = new BasicDataSource();

        //conexão para mysql worckbench
//        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        datasource.setUrl("jdbc:mysql://localhost:3306/grupo9?useTimezone=true&serverTimezone=UTC"); //grupo9 = meu database
//
//        datasource.setUsername("root");
//
//        datasource.setPassword("jujuba123");
        //conexao para sql server
        datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        datasource.setUrl("jdbc:sqlserver://svr-tech-health.database.windows.net:1433;database=grupo9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");

        datasource.setUsername("admin-tech-health");
        datasource.setPassword("#Gfgrupo9");
       

        conexao = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConexao() {
        return conexao;
    }
}

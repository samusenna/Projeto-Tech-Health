
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rmsouza
 */
public class ConexaoTechHealth {
    private JdbcTemplate conexao;

    
    
    public ConexaoTechHealth() {
        
        BasicDataSource datasource = new BasicDataSource();

        //conexao para h2
//        datasource.setDriverClassName("org.h2.Driver");
//        
//        datasource.setUrl("jdbc:h2:file:./meu_banco");
//               
//        datasource.setUsername("sa");
//
//        datasource.setPassword("");
        
        //conexão para mysql worckbench
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        datasource.setUrl("jdbc:mysql://localhost:3306/grupo9?useTimezone=true&serverTimezone=UTC"); //grupo9 = meu database
        
        datasource.setUsername("root");
        
        datasource.setPassword("jujuba123");
        
        conexao = new JdbcTemplate(datasource);
        
    }

    public JdbcTemplate getConexao() {
        return conexao;
    }
    
    
    
    
}

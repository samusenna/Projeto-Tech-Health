/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medidas;

import java.io.IOException;
import org.json.JSONObject;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.h2.util.LocalDateTimeUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/**
 *
 * @author rmsouza
 */
public class MainMedidas {

    public static void main(String[] args) throws IOException, InterruptedException {

        //CONEXAO BANCO
        ConexaoTechHealth connection = new ConexaoTechHealth();
        JdbcTemplate banco = connection.getConexao();
        JSONObject json = new JSONObject();

        //INSTANCIAS
        Looca looca = new Looca();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        Conversor conversor = new Conversor();

        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        ProcessoGrupo grupoProcesso = new ProcessoGrupo();
        List<Processo> processos = grupoProcesso.getProcessos();

        //CRIANDO VARIAVEIS PRA PEGAR AS MEDIDAS
        //Long memoria_Em_Uso = Conversor.formatarBytes(memoria.getEmUso()); -------- PS: essa foi direto no insert       
        //Double percent_Uso_Ram_Processo = processo.getUsoMemoria();
        //Long numero_Leituras_Disco = disco.getLeituras();
        //INSERINDO VALORES NA TABELA de 5 em 5 segundos
        

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                //variaveis
                //fk do computador
                //Integer fkComp = listaMaquinas.size();
                Double uso_Processador = processador.getUso() / 10;

                for (Processo itemProcesso : processos) {

                    for (int cont = (processos.size() - 1); cont < processos.size(); cont++) {

                        //variaveis
                        Double uso_Cpu_Processo = itemProcesso.getUsoCpu();
                        Double uso_Ram_Processo = itemProcesso.getUsoMemoria() * 100000;

                        for (Disco itemDisco : discos) {

                            // for (int contDisco = (discos.size() - 1); contDisco < discos.size(); contDisco++) {
                            //variaveis pra pegar o momento da medida
                            Date dataHoraAtual = new Date();
                            String data = new SimpleDateFormat("dd/MM/yyyy ").format(dataHoraAtual);
                            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
                            String momento = data + hora;

                            Double memoria_Em_Uso = Double.parseDouble(Conversor.formatarBytes(memoria.getEmUso()).replace("GiB", "").replaceAll(",", "."));

                            String discoTotal = Conversor.formatarBytes(itemDisco.getTamanho());
                            String totalDiscoNumber = discoTotal.replace("GiB", "").replaceAll(",", ".");
                            Double tamanho_Disco = Double.parseDouble(totalDiscoNumber);

                            String usoDiscoGb = Conversor.formatarBytes(itemDisco.getBytesDeEscritas() + itemDisco.getBytesDeLeitura());
                            String usoNumbersOnly = usoDiscoGb.replace("GiB", "").replaceAll(",", ".");
                            Double usoDisco = Double.parseDouble(usoNumbersOnly);

                            //Double tamanho_Disco = Double.parseDouble(Conversor.formatarBytes(itemDisco.getTamanho()).substring(0, 5).replaceAll(",", "."));
                            Double capacidade_Total_Memoria = Double.parseDouble(Conversor.formatarBytes(memoria.getTotal()).substring(0, 5).replaceAll(",", "."));

                            Double percent_Memoria_Em_Uso = (memoria_Em_Uso * 100) / capacidade_Total_Memoria;
                            Double percent_Uso_Disco = (usoDisco * 100) / tamanho_Disco;                          
                            
                            banco.update("INSERT INTO medida VALUES (?, ?, ?, ?, ?, ?, ?)",
                                    //null,
                                    percent_Memoria_Em_Uso,
                                    uso_Cpu_Processo,
                                    uso_Processador, uso_Ram_Processo,
                                    percent_Uso_Disco, momento, 1);

                            System.out.println("MEDIDAS INSERIDAS");
                            
                            if (percent_Memoria_Em_Uso > 70.0){
                                json.put("text", "ID da máquina: 1\n"
                                        + "Memoria acima do limite\n" + 
                                         "Memoria: " + percent_Memoria_Em_Uso + 
                                        "Momento: " + momento);
                            }
                            

                            //colocando as medidas numa lista
                            List<Medida> listaMedidas = banco.query("select maquina.idMaquina, idMedida, maquina.ala_Hospitalar, percent_Memoria_Em_Uso, "
                                    + "uso_Cpu_Processo, uso_Processador, uso_Ram_Processo, "
                                    + "percent_Uso_Disco, momento from medida right join maquina "
                                    + "on medida.fkMaquina = maquina.idMaquina "
                                    + "where fkMaquina = ? ",
                                    new BeanPropertyRowMapper(Medida.class), 1);

                            //mostrando a ultima medida inserida
                            System.out.println("\n" + listaMedidas.get(listaMedidas.size() - 1));
                        }
                        //break;
                        try {
                            Slack.sendMessage(json);
                        } catch (Exception e) {
                            System.out.println("");
                        }

                        //}
                    }

                    break;

                }

            }

        }, 0, 5000);

        //
//        List<Medida> listaMedidas = banco.query("select maquina.idMaquina, maquina.ala_Hospitalar, memoria_Em_Uso,"
//                + "percent_Uso_Cpu_Processo, percent_Uso_Processador, percent_Uso_Ram_Processo,"
//                + "numero_Leituras_Disco, momento from medida left join maquina "
//                + "on medida.fkMaquina = maquina.idMaquina"
//                + "order by idMedida desc;", new BeanPropertyRowMapper(Medida.class));
//        for (Medida itemMedida : listaMedidas) {
//            System.out.println(itemMedida);
//        }
    }

}

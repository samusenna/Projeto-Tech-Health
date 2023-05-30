/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medidas;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.github.britooo.looca.api.util.Conversor;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmsouza
 */
public class MainComputador {

    public static void main(String[] args) throws IOException {

        //CONEXAO BANCO
        ConexaoTechHealth connection = new ConexaoTechHealth();
        JdbcTemplate banco = connection.getConexao();

        //vm
        ConexaoDocker connection2 = new ConexaoDocker();
        JdbcTemplate bancoVM = connection2.getConexao();

//INSTANCIANDO CLASSES
        Computador comp = new Computador();
        Medida medida = new Medida();
        Hospital hospital = new Hospital();
        JSONObject json = new JSONObject();
        Funcionario loginFuncionario = new Funcionario();
        List<Integer> listaFK = new ArrayList<>();
        Integer verNome = 0;

        Integer cont = 0;
        Integer login = 0;
        Integer respID = 0;

        List<Integer> listaFkComp = new ArrayList<>();

        while (cont != 1) {
            System.out.println("Digite seu email: ");
            Scanner leitor1 = new Scanner(System.in);
            String strEmail = leitor1.nextLine();

            System.out.println("Digite sua senha: ");
            Scanner leitor2 = new Scanner(System.in);
            String strSenha = leitor2.nextLine();

            //puxando os dados do usuario do banco de dados
            List<Funcionario> listaFuncionarios = banco.query("SELECT email, senha FROM usuario", new BeanPropertyRowMapper(Funcionario.class));

            for (Funcionario itemFuncionario : listaFuncionarios) {

                if (itemFuncionario.getEmail().equals(strEmail) && itemFuncionario.getSenha().equals(strSenha)) {
                    login++;
                    cont++;
                    break;
                }
            }
            if (login == 0) {
                System.out.println("Login errado. Tente novamente!");
            }

        }

        Integer contSmaq = 0;
        if (login > 0) {
            System.out.println("LOGIN EFETUADO COM SUCESSO");

            Integer contCorreto = 0;

            while (contCorreto == 0) {
                System.out.println("Você já possui uma máquina cadastrada com as informações de hardware? [s/n]");
                Scanner leitor4 = new Scanner(System.in);
                String respMaquina = leitor4.nextLine();

                if (respMaquina.equals("s")) {
                    System.out.println("Qual o ID dela?");
                    Scanner leitor5 = new Scanner(System.in);
                    respID = leitor5.nextInt();

                    Integer contID = 0;

                    List<Computador> listaMaquinasID = banco.query("select idMaquina from maquina where idMaquina = ?", new BeanPropertyRowMapper(Computador.class), respID);
                    for (Computador itemMaquinaID : listaMaquinasID) {
                        if (respID == listaMaquinasID.size()) {
                            contID++;
                        }
                    }

                    if (contID > 0) {
                        System.out.println("Você possui a máquina!");
                        contCorreto++;
                        verNome = respID;

                        listaFK.add(respID);

                    } else {
                        System.out.println("ID não encontrado!");
                    }
                } else {
                    contSmaq++;
                    System.out.println("\nVamos continuar o cadastro!");
                    break;
                }

            }

        }

        if (contSmaq > 0) {

            //log
            FileWriter arq;
            if (comp.pegarNomeFabricante().equals("Microsoft")) {
                arq = new FileWriter("C:\\Users\\Public//maquina.txt");

            } else {
                arq = new FileWriter("/home/ubuntu/Desktop//maquina.txt");

            }
            PrintWriter gravarArq = new PrintWriter(arq);

            //CADASTRO DA MAQUINA
            System.out.println("\n\nCADASTRO DA MÁQUINA");

            //fkHospital
            List<Hospital> listaIDhospital = banco.query("select idHospital from hospital", new BeanPropertyRowMapper(Hospital.class));

            List<Hospital> listaHospitais = banco.query("select idHospital, nome_Hospital, cnpj, endereco, bairro, cidade, uf  from hospital"
                    + " where idHospital = ?", new BeanPropertyRowMapper(Hospital.class), listaIDhospital.size());

            Integer fkHospital = listaIDhospital.size();
            List<Computador> listaIDmaq = banco.query("select idMaquina from maquina", new BeanPropertyRowMapper(Computador.class));

            List<String> listaAla = banco.query("select ala_Hospitalar from maquina", new BeanPropertyRowMapper(Ala.class));

            //azure
            banco.update("UPDATE maquina set sistema_Operacional = ?, fabricante = ?, nome_Processador = ?, frequencia_Processador = ?, "
                    + "capacidade_Total_Memoria = ?, tamanho_Disco = ?, numero_CPU_fisica =  ?, fkHospital = ? where idMaquina = ?",
                    comp.pegarNomeSistemaOperacional(), comp.pegarNomeFabricante(), comp.pegarNome_Processador(),
                    comp.pegarFrequencia(), comp.pegarMemoria_Total(), comp.pegarTamanho_Disco(), comp.pegarNumero_CPU_fisica(),
                    fkHospital, listaIDmaq.size());

            System.out.println("\nMáquina inserida no Azure\n");

            //vm
            String ala = listaAla.get(listaAla.size()-1);

            bancoVM.update("INSERT INTO maquina VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)",
                    listaIDmaq.size(), ala, comp.pegarNomeSistemaOperacional(), comp.pegarNomeFabricante(), comp.pegarNome_Processador(),
                    comp.pegarFrequencia(), comp.pegarMemoria_Total(), comp.pegarTamanho_Disco(), comp.pegarNumero_CPU_fisica(), 1);

            System.out.println("\nMaquina inserida no MYSQL");

            List<Computador> listaMaquinas = banco.query("select Hospital.nome_Hospital, idMaquina, ala_Hospitalar, sistema_Operacional, "
                    + "fabricante, nome_Processador, "
                    + "frequencia_Processador, capacidade_Total_Memoria, tamanho_Disco, numero_CPU_fisica from maquina\n"
                    + " right join hospital on maquina.fkHospital = hospital.idHospital where idMaquina = ?;", new BeanPropertyRowMapper(Computador.class), 1);

            listaFkComp.add(listaIDmaq.size());
            comp.setIdMaquina(listaIDmaq.size());

            System.out.println("\nEssa é a sua máquina:");
            for (Computador itemMaquina : listaMaquinas) {
                //Integer contMostrar = listaMaquinas.size();
                if (listaMaquinas.size() == 1) {
                    System.out.println(itemMaquina);
                } else {
                    System.out.println("nao foi o print da maq");
                }

            }
            Date dataHoraAtual = new Date();
            String dataLog = new SimpleDateFormat("dd/MM/yyyy ").format(dataHoraAtual);
            String horaLog = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            String momentoLog = dataLog + horaLog;

            //ARRUMAR A ALA!!!!
            //gravando arquivo txt para o log
            gravarArq.printf("Id Maquina: %d\n\n"
                    + "Ala Hospitalar: %s\n\n"
                    + "Sistema Operacional: %s\n\n"
                    + "Fabricante: %s\n\n"
                    + "Nome do Processador: %s\n\n"
                    + "Frequencia do Processador: %.2f\n\n"
                    + "Capacidade da Memoria: %.2f\n\n"
                    + "Tamanho do Disco: %.2f\n\n"
                    + "Numero de CPUs Fisicas: %d\n\n"
                    + "Momento: %s\n\n\n", 1, ala, comp.pegarNomeSistemaOperacional(), comp.pegarNomeFabricante(),
                    comp.pegarNome_Processador(), comp.pegarFrequencia(), comp.pegarMemoria_Total(), comp.pegarTamanho_Disco(),
                    comp.pegarNumero_CPU_fisica(), momentoLog
            );

            //log
            gravarArq.printf("+-------------+%n\n");
            arq.close();
            if (comp.pegarNomeFabricante().equals("Microsoft")) {
                System.out.println("\nMaquina gravada no log, para ver basta seguir esse caminho: C:\\Users\\Public//maquina.txt\n");
            } else {
                System.out.println("\nMaquina gravada no log, para ver basta seguir esse caminho: /home/ubuntu/Desktop//maquina.txt\n");

            }

        }
        List<Computador> listaMaquinas2 = banco.query("select Hospital.nome_Hospital, idMaquina, ala_Hospitalar, sistema_Operacional, "
                + "fabricante, nome_Processador, "
                + "frequencia_Processador, capacidade_Total_Memoria, tamanho_Disco, numero_CPU_fisica from maquina\n"
                + " right join hospital on maquina.fkHospital = hospital.idHospital where idMaquina = ?;", new BeanPropertyRowMapper(Computador.class), verNome);

        if (contSmaq == 0) {

            for (Computador itemMaquina : listaMaquinas2) {
                if (verNome == listaMaquinas2.size()) {
                    System.out.println("\nInformações de sua máquina: ");
                    System.out.println(itemMaquina);
                }

            }
        }
        System.out.println("\nInserindo medidas!\n");

        //INSERINDO MEDIDAS NA TABELA de 5 em 5 segundos
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Integer fkComp = 0;
                if (listaFK.size() > 0) {
                    //fk vira id respondido
                    fkComp = listaFK.get(0);
                } else {
                    //fk vira o ultimo id da maquina cadastrada
                    fkComp = listaFkComp.size();
                }

                //variaveis pra pegar o momento da medida
                Date dataHoraAtual = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy ").format(dataHoraAtual);
                String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
                String momento = data + hora;

                //insert azure
                banco.update("INSERT INTO medida VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                        medida.pegarPercent_Memoria(), medida.pegarCPU_Processo(),
                        medida.pegarUsoProcessador(), medida.pegarUsoRAM(),
                        medida.pegarPercentDisco(), momento, fkComp);

                //insert mysql
                bancoVM.update("INSERT INTO medida VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                null,medida.pegarPercent_Memoria(), medida.pegarCPU_Processo(), medida.pegarUsoProcessador(), 
                medida.pegarUsoRAM(), medida.pegarPercentDisco(), momento, fkComp);
                
                System.out.println("\nMedidas inseridas!\n");

                //slack
                if (medida.pegarPercent_Memoria() > 60.0) {
                    json.put("text", "ID da máquina: " + 1
                            + "\nMemoria chegando perto do limite!!!"
                            + "\nMemoria: " + medida.pegarPercent_Memoria()
                            + "\nMomento: " + momento);
                }

                try {
                    Slack.sendMessage(json);
                } catch (Exception e) {
                    System.out.println("slack não foi");
                }

                //colocando as medidas numa lista
                List<Medida> listaMedidas = banco.query("select maquina.idMaquina, idMedida, maquina.ala_Hospitalar, percent_Memoria_Em_Uso, "
                        + "uso_Cpu_Processo, uso_Processador, uso_Ram_Processo, "
                        + "percent_Uso_Disco, momento from medida right join maquina "
                        + "on medida.fkMaquina = maquina.idMaquina "
                        + "where fkMaquina = ? ",
                        new BeanPropertyRowMapper(Medida.class), fkComp);

                //mostrando a ultima medida inserida
                System.out.println("\n" + listaMedidas.get(listaMedidas.size() - 1));

            }
        }, 0, 5000);

    }
}

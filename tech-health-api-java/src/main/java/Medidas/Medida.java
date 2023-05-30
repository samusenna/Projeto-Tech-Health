/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medidas;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;

/**
 *
 * @author rmsouza
 */
public class Medida {

    Memoria memoria = new Memoria();
    Processador processador = new Processador();
    Conversor conversor = new Conversor();

    ProcessoGrupo grupoProcesso = new ProcessoGrupo();
    List<Processo> processos = grupoProcesso.getProcessos();
    DiscoGrupo grupoDeDiscos = new DiscoGrupo();
    List<Disco> discos = grupoDeDiscos.getDiscos();

    private Integer idMedida;
    private Integer idMaquina;
    private String ala_Hospitalar;
    private Double percent_Memoria_Em_Uso;
    private Double uso_Cpu_Processo;
    private Double uso_Processador;
    private Double uso_Ram_Processo;
    private Double percent_Uso_Disco;
    private String momento;

    public Double pegarPercent_Memoria() {
        Double memoria_Em_Uso = 0.0;
        if (Conversor.formatarBytes(memoria.getEmUso()).contains("MiB")) {
            memoria_Em_Uso = Double.parseDouble(Conversor.formatarBytes(memoria.getEmUso()).replace("MiB", "").replaceAll(",", "."));
        }
        if (Conversor.formatarBytes(memoria.getEmUso()).contains("GiB")) {
            memoria_Em_Uso = Double.parseDouble(Conversor.formatarBytes(memoria.getEmUso()).replace("GiB", "").replaceAll(",", "."));
        }

        Double capacidade_Total_Memoria = Double.parseDouble(Conversor.formatarBytes(memoria.getTotal()).replace("GiB", "").replaceAll(",", "."));
        Double percent_Memoria_Em_Uso = (memoria_Em_Uso * 100) / capacidade_Total_Memoria;
        return percent_Memoria_Em_Uso;
    }

    public Double pegarCPU_Processo() {
        Double uso_Cpu_Processo = 0.0;
        for (Processo itemProcesso : processos) {

            for (int cont = (processos.size() - 1); cont < processos.size(); cont++) {
                uso_Cpu_Processo = itemProcesso.getUsoCpu();
            }
            break;
        }
        return uso_Cpu_Processo + 1 * 10;

    }

    public Double pegarUsoRAM() {
        Double uso_Ram_Processo = 0.0;
        for (Processo itemProcesso : processos) {

            for (int cont = (processos.size() - 1); cont < processos.size(); cont++) {
                uso_Ram_Processo = itemProcesso.getUsoMemoria() * 100000;
            }
            break;
        }
        return uso_Ram_Processo;
    }

    public Double pegarPercentDisco() {
        Double percent_Uso_Disco = 0.0;
        for (Disco itemDisco : discos) {
            for (int contDisco = (discos.size() - 1); contDisco < discos.size(); contDisco++) {

                String usoDiscoGb = Conversor.formatarBytes(itemDisco.getBytesDeEscritas() + itemDisco.getBytesDeLeitura());
                String usoNumbersOnly = "";
                if (Conversor.formatarBytes(itemDisco.getBytesDeEscritas() + itemDisco.getBytesDeLeitura()).contains("GiB")) {
                    usoNumbersOnly = usoDiscoGb.replace("GiB", "").replaceAll(",", ".");
                }
                if (Conversor.formatarBytes(itemDisco.getBytesDeEscritas() + itemDisco.getBytesDeLeitura()).contains("MiB")) {
                    usoNumbersOnly = usoDiscoGb.replace("MiB", "").replaceAll(",", ".");
                }

                Double usoDisco = Double.parseDouble(usoNumbersOnly);
                Double tamanho_Disco = Double.parseDouble(Conversor.formatarBytes(itemDisco.getTamanho()).replace("GiB", "").replaceAll(",", "."));
                percent_Uso_Disco = (usoDisco * 100) / tamanho_Disco;

            }
            break;
        }
        return percent_Uso_Disco;
    }

    public Double pegarUsoProcessador() {
        Double uso_Processador = processador.getUso();
        return uso_Processador;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getAla_Hospitalar() {
        return ala_Hospitalar;
    }

    public void setAla_Hospitalar(String ala_Hospitalar) {
        this.ala_Hospitalar = ala_Hospitalar;
    }

    public Double getPercent_Memoria_Em_Uso() {
        return percent_Memoria_Em_Uso;
    }

    public void setPercent_Memoria_Em_Uso(Double percent_Memoria_Em_Uso) {
        this.percent_Memoria_Em_Uso = percent_Memoria_Em_Uso;
    }

    public Double getUso_Cpu_Processo() {
        return uso_Cpu_Processo;
    }

    public void setUso_Cpu_Processo(Double uso_Cpu_Processo) {
        this.uso_Cpu_Processo = uso_Cpu_Processo;
    }

    public Double getUso_Processador() {
        return uso_Processador;
    }

    public void setUso_Processador(Double uso_Processador) {
        this.uso_Processador = uso_Processador;
    }

    public Double getUso_Ram_Processo() {
        return uso_Ram_Processo;
    }

    public void setUso_Ram_Processo(Double uso_Ram_Processo) {
        this.uso_Ram_Processo = uso_Ram_Processo;
    }

    public Double getPercent_Uso_Disco() {
        return percent_Uso_Disco;
    }

    public void setPercent_Uso_Disco(Double percent_Uso_Disco) {
        this.percent_Uso_Disco = percent_Uso_Disco;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        //StringBuilder stb = new StringBuilder();

        sb.append("\nID da Medida: ")
                .append(getIdMedida())
                .append("\n");

        sb.append("\nID da maquina: ")
                .append(getIdMaquina())
                .append("\n");

        sb.append("Ala Hospitalar: ")
                .append(getAla_Hospitalar())
                .append("\n");

        sb.append("Memoria em Uso: ")
                .append(getPercent_Memoria_Em_Uso())
                .append(" %")
                .append("\n");

//        sb.append("Frequencia do Processador:")
//                .append(getFrequencia_Processador())
//                .append("\n");
        sb.append("Uso da CPU no processo: ")
                .append(getUso_Cpu_Processo())
                .append("\n");

        sb.append("Uso do Processador:")
                .append(getUso_Processador())
                .append("\n");

        sb.append("Uso da RAM no processo:")
                .append(getUso_Ram_Processo())
                .append("\n");

        sb.append("Disco em Uso: ")
                .append(getPercent_Uso_Disco())
                .append(" %")
                .append("\n");

        sb.append("Momento: ")
                .append(getMomento())
                .append("\n");

        return sb.toString();

    }

}

var database = require("../database/config");

function buscarUltimasMedidas(idMaquina, limite_linhas) {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `select top ${limite_linhas} percent_Memoria_Em_Uso, uso_Cpu_Processo, uso_Processador,
        uso_Ram_Processo, percent_Uso_Disco, replace(momento, '/2022', '') as momento_grafico from
        medida where fkMaquina = ${idMaquina} order by idMedida desc`;

    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `select percent_Memoria_Em_Uso, uso_Cpu_Processo, uso_Processador,
        uso_Ram_Processo, percent_Uso_Disco, DATE_FORMAT(date_add(momento, INTERVAL second(momento) * -1 SECOND),'%H:%i:%s') as momento_grafico from
        medida where fkMaquina = ${idMaquina} order by idMedida desc limit ${limite_linhas}`;

    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarMedidasEmTempoReal(idMaquina) {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `select top 1
        percent_Memoria_Em_Uso, uso_Cpu_Processo, uso_Processador,
        uso_Ram_Processo, percent_Uso_Disco, replace(momento, '/2022', '') as momento_grafico from
        medida where fkMaquina = ${idMaquina} order by idMedida desc`;

    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `select top 1
        percent_Memoria_Em_Uso, uso_Cpu_Processo, uso_Processador,
        uso_Ram_Processo, percent_Uso_Disco, replace(momento, '/2022', '') as momento_grafico from medida join maquina on
        medida.fkMaquina = ${idMaquina} where fkMaquina = ${idMaquina}`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

module.exports = {
    buscarUltimasMedidas,
    buscarMedidasEmTempoReal
}

var database = require("../database/config");

function buscarUsuarios() {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `select count(id) as contagemUsuarios from usuario`;

    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `select count(id) as contagemUsuarios from usuario}`;

    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarInfosIndex() {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `select count(idMaquina) as contagemMaq, count (distinct ala_Hospitalar) as alas from maquina`;

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

function mostrarHospital() {
    var instrucao = ` select nome_Hospital as nome_Hospital, maquina.Idmaquina as idMaq from hospital 
    join maquina on hospital.idHospital = maquina.fkHospital order by idHospital desc
               `;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

module.exports = {
    buscarUsuarios,
    buscarInfosIndex,
    mostrarHospital
}
 
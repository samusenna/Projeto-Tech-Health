var database = require("../database/config")

// Coloque os mesmos parâmetros aqui. Vá para a var instrucao
function confirma(ala_Hospitalar) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function confirma(ala_Hospitalar):", ala_Hospitalar);
    // console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function confirmar():", ala_Hospitalar);

    
    // Insira exatamente a query do banco aqui, lembrando da nomenclatura exata nos valores
    //  e na ordem de inserção dos dados.
    var instrucao = `
        INSERT INTO maquina (ala_Hospitalar) VALUES 
        ('${ala_Hospitalar}');
    `;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

// function remove(idMaquina) {
//     console.log("ACESSEI O AVISO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function remove():", idMaquina);
//     var instrucao = `
//         DELETE FROM maquina WHERE idMaquina = ${idMaquina};
//     `;
//     console.log("Executando a instrução SQL: \n" + instrucao);
//     return database.executar(instrucao);
// }

function cadastrar(nome_Hospital, cnpj, endereco, bairro, cidade, uf) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function cadastrar():", nome_Hospital, cnpj, endereco, bairro, cidade, uf);
    
    // Insira exatamente a query do banco aqui, lembrando da nomenclatura exata nos valores
    //  e na ordem de inserção dos dados.
    var instrucao = `
        INSERT INTO hospital (nome_Hospital, cnpj, endereco, bairro, cidade, uf) VALUES ('${nome_Hospital}', '${cnpj}','${endereco}', '${bairro}', '${cidade}', '${uf}');
    `;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

function mostrarInfo(idMaquina) {
      
    // Insira exatamente a query do banco aqui, lembrando da nomenclatura exata nos valores
    //  e na ordem de inserção dos dados.
    var instrucao = ` select idMaquina as id, ala_Hospitalar as ala, sistema_Operacional as so, capacidade_Total_Memoria as totalMemoria, 
    tamanho_Disco as totalDisco from maquina where idMaquina = ${idMaquina}
               `;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}


module.exports = {
    confirma,
    // remove, 
    cadastrar,
    mostrarInfo
    
};  
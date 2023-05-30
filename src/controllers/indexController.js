var indexModel = require("../models/indexModel");

function buscarUsuarios(req, res) {

    console.log(`Recuperando medidas em tempo real`);

    indexModel.buscarUsuarios().then(function (resultado) {
        if (resultado.length > 0) {
            res.json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}
function buscarInfosIndex(req, res) {

    console.log(`Recuperando medidas em tempo real`);

    indexModel.buscarInfosIndex().then(function (resultado) {
        if (resultado.length > 0) {
            res.json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function mostrarHospital(req, res) {

    console.log(`Recuperando medidas em tempo real`);

    indexModel.mostrarHospital().then(function (resultado) {
        if (resultado.length > 0) {
            res.json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}


module.exports = {
    buscarUsuarios,
    buscarInfosIndex,
    mostrarHospital
    
} 
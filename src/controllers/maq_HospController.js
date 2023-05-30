var maq_HospModel = require("../models/maq_HospModel");

var sessoes = [];

function confirma(req, res) {
    // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
    var ala_Hospitalar = req.body.ala_HospitalarServer;
    
    // Faça as validações dos valores
    if (ala_Hospitalar == undefined) {
        res.status(400).send("Ala Hospitalar não definida pelo usuário");
    } else {
        
        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        maq_HospModel.confirma(ala_Hospitalar)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}

// function remove(req, res) {
//     var idMaquina = req.params.idMaquina;

//     maq_HospModel.remove(idMaquina)
//         .then(
//             function (resultado) {
//                 res.json(resultado);
//             }
//         )
//         .catch(
//             function (erro) {
//                 console.log(erro);
//                 console.log("Houve um erro ao deletar maquina: ", erro.sqlMessage);
//                 res.status(500).json(erro.sqlMessage);
//             }
//         );
// }

function cadastrar(req, res) {
    // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
    var nome_Hospital = req.body.nome_HospitalServer;
    var cnpj = req.body.cnpjServer;
    var endereco = req.body.enderecoServer;
    var bairro = req.body.bairroServer;
    var cidade = req.body.cidadeServer;
    var uf = req.body.ufServer;

    // Faça as validações dos valores
    if (nome_Hospital == undefined) {
        res.status(400).send("Seu nome está undefined!");
    } else if (cnpj == undefined) {
        res.status(400).send("Seu CNPJ está undefined!");
    } else if (endereco == undefined) {
        res.status(400).send("Seu endereco está undefined!");
    }else if (bairro == undefined) {
        res.status(400).send("Seu bairro está undefined!");
    } else if (cidade == undefined) {
        res.status(400).send("Sua cidade está undefined!");
    } else {
        
        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        maq_HospModel.cadastrar(nome_Hospital, cnpj, endereco, bairro, cidade, uf)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}

function mostrarInfo(req, res) {

    var idMaquina = req.params.idMaquina;

    console.log(`Recuperando medidas em tempo real`);

    maq_HospModel.mostrarInfo(idMaquina).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
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
    confirma,
    // remove,
    cadastrar,
    mostrarInfo
}
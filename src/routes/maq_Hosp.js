var express = require("express");
var router = express.Router();

var maq_HospController = require("../controllers/maq_HospController");

//Recebendo os dados do html e direcionando para a função cadastrar de usuarioController.js
router.post("/confirma", function (req, res) {
    maq_HospController.confirma(req, res);
});

// router.delete("/remove/:idMaquina", function (req, res) {
//     maq_HospController.remove(req, res);
// });

router.post("/cadastrar", function (req, res) {
    maq_HospController.cadastrar(req, res);
});

router.get("/mostrarInfoMaq/:idMaquina", function (req, res) {
    maq_HospController.mostrarInfo(req, res);
});




module.exports = router;  
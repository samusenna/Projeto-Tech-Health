var express = require("express");
var router = express.Router();

var indexController = require("../controllers/indexController");

router.get("/buscarUsuarios", function (req, res) {
    indexController.buscarUsuarios(req, res);
});

router.get("/buscarInfoMaquina", function (req, res) {
    indexController.buscarInfosIndex(req, res);
});

router.get("/buscarInfoHospital", function (req, res) {
    indexController.mostrarHospital(req, res);
});

module.exports = router;   
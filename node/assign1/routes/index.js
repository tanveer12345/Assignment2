var express = require('express');
var router = express.Router();
const indexController = require('../controller/indexController')

/* GET home page. */
router.get('/', indexController.homePage);

router.get('/about', indexController.about);
router.get('/projects', indexController.projects);
router.get('/services', indexController.services);
router.get('/contact', indexController.contact);

module.exports = router;

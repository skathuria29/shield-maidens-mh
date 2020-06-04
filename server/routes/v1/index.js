const express = require('express');

const users = require('./users');
const sessions = require('./sessions');

const router = express.Router();

router.use('/users', users);
router.use('/sessions', sessions);

module.exports = router;

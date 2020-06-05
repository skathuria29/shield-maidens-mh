const express = require('express');

const users = require('./users');
const sessions = require('./sessions');
const activities = require('./activities');

const router = express.Router();

router.use('/users', users);
router.use('/sessions', sessions);
router.use('/activities', activities);

module.exports = router;

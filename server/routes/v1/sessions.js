
const {
	createSession,
    updateSession,
	getSession,
	getSessions
} = require('../../controllers/v1/sessions');

const router = require('express-promise-router')();

router
	.get('/:id([A-z0-9-]{1,})', getSession)
	.get('/', getSessions)
    .put('/:id', updateSession)
	.post('/', createSession)
	

module.exports = router;

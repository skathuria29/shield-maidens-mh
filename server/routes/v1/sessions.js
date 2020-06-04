
const {
	createSession,
    updateSession,
	getSession,
	getSessions
} = require('../../controllers/v1/sessions');
// const {
// 	// validateCreateUser,
// 	validateGetSessionById
// 	// validateUserAuthenticate,
// 	// validateUpdateUser
// } = require('../../middlewares/validators/users');
const { validationErrorHandler } = require('../../middlewares/index');

const router = require('express-promise-router')();

router
	.get('/:id([A-z0-9-]{1,})', getSession)
	.get('/', getSessions)
    .put('/:id', updateSession)
    .post('/create', createSession)
	

module.exports = router;

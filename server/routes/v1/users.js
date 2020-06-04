
const {
	createUser,
	authenticateUser,
    updateUser,
	getUser,
	getUsers
} = require('../../controllers/v1/users');
const {
	validateCreateUser,
	validateGetUserById,
	validateUserAuthenticate,
	validateUpdateUser
} = require('../../middlewares/validators/users');
const { validationErrorHandler } = require('../../middlewares/index');

const router = require('express-promise-router')();

router
	.get('/:id([A-z0-9-]{1,})', getUser)
	.get('/', getUsers)
	.put('/:id', validateUpdateUser(), validationErrorHandler, updateUser)
	.post('/authenticate', validateUserAuthenticate(), authenticateUser)
	.post('/registration', validateCreateUser(), validationErrorHandler, createUser);

module.exports = router;

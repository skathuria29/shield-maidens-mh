const { checkSchema, check } = require('express-validator');

const { isNotEmpty } = require('../../helpers/schemaValidations');

// function __validateEmail() {
// 	return [check('email').not().isEmpty().isEmail()];
// }

// function validateCreateSession() {
// 	return checkSchema({
// 		email: {
// 			in: ['body'],
// 			...isNotEmpty,
// 			errorMessage: 'Email should not be empty'
// 		},
// 		password: {
// 			in: ['body'],
// 			...isNotEmpty,
// 			custom: {
// 				options: (value, { req }) => {
// 					if (!value || !value.trim().length) throw new Error('Passwords cannot be empty');
// 					else if (value !== req.body.confirmPassword) throw new Error("Passwords doesn't match");

// 					return true;
// 				}
// 			}
// 		},
// 		confirmPassword: {
// 			in: ['body'],
// 			...isNotEmpty,
// 			custom: {
// 				options: (value, { req }) => {
// 					if (!value || !value.trim().length) throw new Error('Passwords cannot be empty');
// 					else if (value !== req.body.password) throw new Error("Passwords doesn't match");

// 					return true;
// 				}
// 			}
// 		}
// 	});
// }

function validateGetSessionById() {
	return [check('id').not().isEmpty()];
}

// function validateUserAuthenticate() {
// 	return [...__validateEmail(), check('password').not().isEmpty()];
// }

// function validateUpdateSession() {
// 	return checkSchema({
// 		id: {
// 			in: ['params'],
// 			...isNotEmpty,
// 			errorMessage: 'User id should not be empty'
// 		},
// 		password: {
// 			in: ['body'],
// 			custom: {
// 				options: (value) => {
// 					if (value && !value.trim().length) throw new Error('Passwords cannot be empty');
// 					return true;
// 				}
// 			}
// 		},
// 		username: {
// 			in: ['body'],
// 			custom: {
// 				options: (value) => {
// 					if (!value || !value.trim().length) throw new Error('Username cannot be empty');
// 					return true;
// 				}
// 			}
// 		}
// 	});
// }

module.exports = {
	// validateCreateUser,
	validateGetSessionById,
	// validateUserAuthenticate,
	// validateUpdateUser
};

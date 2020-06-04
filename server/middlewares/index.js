const { validationResult } = require('express-validator');

const { BAD_REQUEST } = require('../constants/httpStatuses');
const CustomError = require('../helpers/customError');

function validationErrorHandler(req, res, next) {
	const { errors } = validationResult(req);

	if (errors && errors.length) {
		let summary = '';
		const errorDetails = errors.map(({ param, msg }) => {
			summary += `${msg}\n`;
			return { param, message: msg };
		});

		return next(
			new CustomError({
				status: BAD_REQUEST,
				data: errorDetails,
				summary
			})
		);
	}

	next();
}

module.exports = {
	validationErrorHandler
};

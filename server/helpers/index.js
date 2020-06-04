const { OK, INTERNAL_SERVER_ERROR } = require('../constants/httpStatuses');
const CustomError = require('../helpers/customError');

function __validateResponseObject(res) {
	if (!res) {
		console.log('Res Object not found while sending success response');
		throw new Error(
			JSON.stringify({
				status: INTERNAL_SERVER_ERROR,
				data: {
					message: 'Something went wrong!'
				}
			})
		);
	}
}

function __sendResponse(data, res, status) {
	__validateResponseObject(res);
	return res.status(status).json(data);
}

function sendSuccessResponse(data = {}, res = false, status = OK) {
	return __sendResponse(data, res, status);
}

function sendErrorResponse(data = {}, res = false, status = INTERNAL_SERVER_ERROR) {
	return __sendResponse(data, res, status);
}

function commonErrorHandling(err, data = {}) {
	console.log(err);
	throw new CustomError(data);
}

function transformToArray(value) {
	if (Array.isArray(value)) return value;
	else if (value) return [value];
	else return [];
}

module.exports = {
	sendSuccessResponse,
	sendErrorResponse,
	transformToArray,
	commonErrorHandling
};

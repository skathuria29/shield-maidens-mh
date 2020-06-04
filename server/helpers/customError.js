function CustomError(message) {
	this.name = 'CustomError';
	this.message = JSON.stringify(message || 'Something went wrong');
	this.stack = new Error().stack;
}
CustomError.prototype = Object.create(Error.prototype);
CustomError.prototype.constructor = CustomError;

module.exports = CustomError;

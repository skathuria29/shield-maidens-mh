const _ = require('lodash');
const md5 = require('md5');

const User = require('../../models/User');
const CustomError = require('../../helpers/customError');
const { sendSuccessResponse, sendErrorResponse, commonErrorHandling, setUserCookie } = require('../../helpers/index');
const { BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED } = require('../../constants/httpStatuses');
const { USER_KEYS } = require('../../constants/users');

async function createUser(req, res) {
	try {
		const data = await User.createUser(req.body);
		return sendSuccessResponse(data, res);
	} catch (err) {
		const isCustomError = err instanceof CustomError;

		return commonErrorHandling(err, {
			data: {
				message: !isCustomError ? 'User Creation Failed' : err.message
			},
			status: isCustomError ? BAD_REQUEST : INTERNAL_SERVER_ERROR
		});
	}
}

async function getUser(req, res) {
    try {
        const user = await User.getUser('userId', req.params.id);
        return sendSuccessResponse(user, res);
    } catch(err) {
        commonErrorHandling(err, {
            message: 'User Fetching Failed'
        });
    }
}
async function getUsers(req, res) {
    try {
        const user = await User.getUsers();
        return sendSuccessResponse(user, res);
    } catch(err) {
        commonErrorHandling(err, {
            message: 'User Fetching Failed'
        });
    }
}



async function authenticateUser(req, res) {
	try {
		const user = await User.authenticateUser(req.body.email, req.body.password);
		// await setUserCookie(res, { email: user.email, admin: user.admin, userId: user.userId });
		return sendSuccessResponse(user, res);
	} catch (err) {
		const { type, message = 'Something went wrong' } = err;
		let status = INTERNAL_SERVER_ERROR;

		if (type) status = type === 1 ? BAD_REQUEST : UNAUTHORIZED;

		throw new CustomError({
			status,
			data: { message }
		});
	}
}

async function updateUser(req, res) {
	debugger;
	try {
		const user = { ...req.user };

		if (req.body.hasOwnProperty('username') && req.body.username !== user.username) {
			const user = await User.getUserByUsername(req.body.username);

			if (user)
				return sendErrorResponse(
					{
						message: `User with username ${req.body.username} already exists`
					},
					res,
					BAD_REQUEST
				);
		}

		_.forEach(req.body, (value, key) => {
			if (!USER_KEYS.allowedToUpdate.includes(key) || !USER_KEYS.all.includes(key)) {
				delete req.body[key];
			} else if (_.isArray(value)) {
				user[key] = _.union(user[key], value);
			} else if (_.isObject(value)) {
				user[key] = _.merge(user[key], value);
			} else if (key === 'password') {
				if (value && value.trim().length) user[key] = md5(user.salt + value + user.salt);
			} else {
				user[key] = value;
			}
		});

		user.modifiedAt = +new Date();

		await User.updateUser(req.user.userId, user);
		return sendSuccessResponse(
			{
				message: 'User Updated'
			},
			res
		);
	} catch (err) {
		if (err instanceof CustomError) throw err;

		console.log(err);

		throw new CustomError({
			data: { message: 'User updation failed' }
		});
	}
}

module.exports = {
	createUser,
	getUser,
	authenticateUser,
	updateUser,
	getUsers
};

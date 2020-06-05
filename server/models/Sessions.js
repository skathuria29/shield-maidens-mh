const mongoose = require('mongoose');
const _ = require('lodash');
const randomstring = require('randomstring');
const config = require('config');
const md5 = require('md5');

const { USER_KEYS } = require('../constants/users');
const CustomError = require('../helpers/customError');

const Schema = mongoose.Schema;
const sessionSchema = new Schema(
	{
		sessionId: {
			type: String,
			required: true
		},
		name: {
			type: String,
			default: ''
        },
        professional: {
			type: String,
			default: ''
        },
        type: {
			type: String,
			default: ''
        },
        count: {
            type: Number,
			default: 0
        },
        date: {
            type: String,
			default: ''
        },
		createdAt: {
			type: Number,
			default: +new Date()
		},
		modifiedAt: {
			type: Number,
			default: +new Date()
		},
	},
	{ minimize: false }
);

sessionSchema.statics.createSession = async function (data) {
	// const user = await this.getUserByEmail(data.email);

	// if (user) return Promise.reject(new CustomError(`User with email address ${data.email} already exists`));

	// delete data.confirmPassword;

	data.sessionId = randomstring.generate(config.get('ID_OPTIONS'));
	// data.username = randomstring.generate({
	// 	length: 7,
	// 	charset: 'alphanumeric'
	// });
	data.salt = `${process.env.GLOBAL_SALT}${randomstring.generate({
		length: 10,
		charset: 'alphanumeric'
	})}${process.env.GLOBAL_SALT}`;
	// data.password = md5(data.salt + data.password + data.salt);
	data.createdAt = +new Date();

    console.log("****", data);
	const sessionData = await this.create(data);
	data = { ...data, ...sessionData._doc };

	// USER_KEYS.internals.forEach((key) => delete data[key]);

	return data;
};

sessionSchema.statics.getSessions = async function (condition = {}) {
	return this.find(condition);
};

sessionSchema.statics.getSession = function (key, value) {
	return this.findOne(
		{
			[key]: value
		}
	);
};



// sessionSchema.statics.authenticateUser = async function (email, password) {
// 	let user = await this.getUserByEmail(email);

// 	if (!user)
// 		return Promise.reject({
// 			type: 1,
// 			message: 'No user found with this email'
// 		});
// 	else if (!user.isActive) {
// 		return Promise.reject({
// 			type: 1,
// 			message: 'Account is deactivated. Please contact support.'
// 		});
// 	}

// 	user = user._doc;

//     const hashedPassword = md5(user.salt + password + user.salt);

//     if (user.password !== hashedPassword)
//         return Promise.reject({
//             type: 2,
//             message: 'Invalid email/password combination'
//         });

// 	USER_KEYS.internals.forEach((key) => delete user[key]);
// 	return user;
// };

sessionSchema.statics.updateSession = function (value, data, key = 'userId') {
	return this.updateOne(
		{
			[key]: value
		},
		data
	).then(() => console.log('User Updated'));
};

const Sessions = mongoose.model('sessions', sessionSchema, 'sessions');

module.exports = Sessions;

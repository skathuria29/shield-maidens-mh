const mongoose = require('mongoose');
const _ = require('lodash');
const randomstring = require('randomstring');
const config = require('config');
const md5 = require('md5');

const { USER_KEYS } = require('../constants/users');
const CustomError = require('../helpers/customError');

const Schema = mongoose.Schema;
const userSchema = new Schema(
	{
		userId: {
			type: String,
			required: true
		},
		name: {
			type: String,
			default: ''
		},
		email: {
			type: String,
			required: true
		},
		gender: {
			type: String,
			default: null
		},
		dob: {
			type: Number,
			default: null
		},
		createdAt: {
			type: Number,
			default: +new Date()
		},
		modifiedAt: {
			type: Number,
			default: +new Date()
		},
		password: {
			type: String,
			required: true
		},
		salt: {
			type: String,
			required: true
		}
	},
	{ minimize: false }
);

userSchema.statics.createUser = async function (data) {
	const user = await this.getUserByEmail(data.email);

	if (user) return Promise.reject(new CustomError(`User with email address ${data.email} already exists`));

	delete data.confirmPassword;

	data.userId = randomstring.generate(config.get('ID_OPTIONS'));
	// data.username = randomstring.generate({
	// 	length: 7,
	// 	charset: 'alphanumeric'
	// });
	data.salt = `${process.env.GLOBAL_SALT}${randomstring.generate({
		length: 10,
		charset: 'alphanumeric'
	})}${process.env.GLOBAL_SALT}`;
	data.password = md5(data.salt + data.password + data.salt);
	data.createdAt = +new Date();

	const userData = await this.create(data);
	data = { ...data, ...userData._doc };

	USER_KEYS.internals.forEach((key) => delete data[key]);

	return data;
};

userSchema.statics.getUsers = async function (condition = {}) {
	return this.find(condition, USER_KEYS.available);
};

userSchema.statics.getUser = function (key, value) {
	return this.findOne(
		{
			[key]: value
		},
		USER_KEYS.available
	);
};

userSchema.statics.getUserByEmail = function (email) {
	return this.getUser('email', email);
};

userSchema.statics.getUserById = function (id) {
	return this.getUser('userId', id);
};

userSchema.statics.getUserByUsername = function (username) {
	return this.getUser('username', username);
};

userSchema.statics.authenticateUser = async function (email, password) {
	let user = await this.getUserByEmail(email);

	if (!user)
		return Promise.reject({
			type: 1,
			message: 'No user found with this email'
		});
	else if (!user.isActive) {
		return Promise.reject({
			type: 1,
			message: 'Account is deactivated. Please contact support.'
		});
	}

	user = user._doc;

    const hashedPassword = md5(user.salt + password + user.salt);

    if (user.password !== hashedPassword)
        return Promise.reject({
            type: 2,
            message: 'Invalid email/password combination'
        });

	USER_KEYS.internals.forEach((key) => delete user[key]);
	return user;
};

userSchema.statics.updateUser = function (value, data, key = 'userId') {
	return this.updateOne(
		{
			[key]: value
		},
		data
	).then(() => console.log('User Updated'));
};

const User = mongoose.model('users', userSchema, 'users');

module.exports = User;

const mongoose = require('mongoose');
const _ = require('lodash');
const randomstring = require('randomstring');
const config = require('config');
const md5 = require('md5');

const { USER_KEYS } = require('../constants/users');
const CustomError = require('../helpers/customError');

const Schema = mongoose.Schema;
const activitySchema = new Schema(
	{
		activityId: {
			type: String,
			required: true
		},
		description: {
			type: String,
            default: ''
        },
        days: {
            type: Number,
			default: ''
        },
        type: {
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

activitySchema.statics.createActivity = async function (data) {
	// const user = await this.getUserByEmail(data.email);

	// if (user) return Promise.reject(new CustomError(`User with email address ${data.email} already exists`));

	// delete data.confirmPassword;

	data.activityId = randomstring.generate(config.get('ID_OPTIONS'));
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

	const activityData = await this.create(data);
	data = { ...data, ...activityData._doc };

	// USER_KEYS.internals.forEach((key) => delete data[key]);

	return data;
};

activitySchema.statics.getActivities = async function (condition = {}) {
	return this.find(condition);
};

activitySchema.statics.getActivity = function (key, value) {
	return this.findOne(
		{
			[key]: value
		}
	);
};

activitySchema.statics.updateActivity = function (value, data, key = 'userId') {
	return this.updateOne(
		{
			[key]: value
		},
		data
	).then(() => console.log('User Updated'));
};

const Activities = mongoose.model('activities', activitySchema, 'activities');

module.exports = Activities;

const USER_KEYS = {
	internals: ['salt', 'password', 'forgetPassword'],
	untouchables: ['salt'],
	allowedToUpdate: [
		'name',
		'description',
		'gender',
		'dob',
		'picture',
		'password',
		'username',
		'forgetPassword',
		'modifiedAt'
	],
	available: {
		_id: 1,
		userId: 1,
		name: 1,
		description: 1,
		email: 1,
		picture: 1,
		dob: 1,
		username: 1,
		gender: 1,
		createdAt: 1,
		modifiedAt: 1
	},
	all: [
		'_id',
		'userId',
		'name',
		'description',
		'email',
		'gender',
		'dob',
		'picture',
		'password',
		'createdAt',
		'modifiedAt',
		'salt',
		'username',
		'isActive'
	]
};

module.exports = {
	USER_KEYS
};

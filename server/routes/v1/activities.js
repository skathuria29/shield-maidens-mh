
const {
	createActivity,
    updateActivity,
	getActivity,
	getActivities
} = require('../../controllers/v1/activities');

const router = require('express-promise-router')();

router
	.get('/:id([A-z0-9-]{1,})', getActivity)
	.get('/', getActivities)
    .put('/:id', updateActivity)
	.post('/', createActivity)
	

module.exports = router;

const _ = require('lodash');
const md5 = require('md5');

const Activities = require('../../models/Activity');
const CustomError = require('../../helpers/customError');
const { sendSuccessResponse, sendErrorResponse, commonErrorHandling, setUserCookie } = require('../../helpers/index');
const { BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED } = require('../../constants/httpStatuses');
// const { USER_KEYS } = require('../../constants/users');

async function createActivity(req, res) {
	try {
		const data = await Activities.createActivity(req.body);
		return sendSuccessResponse(data, res);
	} catch (err) {
		const isCustomError = err instanceof CustomError;

		return commonErrorHandling(err, {
			data: {
				message: !isCustomError ? 'Session Creation Failed' : err.message
			},
			status: isCustomError ? BAD_REQUEST : INTERNAL_SERVER_ERROR
		});
	}
}

async function getActivity(req, res) {
    try {
        const activity = await Activities.getActivity('activityId', req.params.id);
        return sendSuccessResponse(activity, res);
    } catch(err) {
        commonErrorHandling(err, {
            message: 'Activity Fetching Failed'
        });
    }
}
async function getActivities(req, res) {
    console.log("in get activities");
    try {
        const activities = await Activities.getActivities(req.query || {});
        return sendSuccessResponse(activities, res);
    } catch(err) {
        commonErrorHandling(err, {
            message: 'Session Fetching Failed'
        });
    }
}

async function updateActivity(req, res) {
	try {
		const activity = { ...req.activity };

		activity.modifiedAt = +new Date();

		await Activities.updateActivity(req.activity.activityId, activity);
		return sendSuccessResponse(
			{
				message: 'Session Updated'
			},
			res
		);
	} catch (err) {
		if (err instanceof CustomError) throw err;

		console.log(err);

		throw new CustomError({
			data: { message: 'Activity updation failed' }
		});
	}
}

module.exports = {
	createActivity,
	getActivity,
	updateActivity,
	getActivities
};

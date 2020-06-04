require('dotenv').config({
	path: '../.env'
});

const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const compression = require('compression');
const helmet = require('helmet');
const config = require('config');
const cors = require('cors');

const router = require('./routes/index');
const db = require('./libs/db');
const { INTERNAL_SERVER_ERROR } = require('./constants/httpStatuses');
const { ENVS } = require('./constants/index');

const app = express();
const startServer = () => {
	return app.listen(config.get('PORT'), () => console.log(`Server is running on ${config.get('PORT')}`));
};

app.use(cors());
app.use(compression());
app.use(helmet());

// Static Middleware
app.use(express.static(path.join(__dirname, 'public')));

// Basic express middleware
app.use(bodyParser.json({ limit: '5mb' }));
app.use(bodyParser.urlencoded({ extended: true, limit: '5mb' }));
app.use(cookieParser());

// Templating Engine
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

process.env.NODE_ENV = process.env.ENV;

router(app);

app.use((err, req, res, next) => {
	console.log('Global Error Handler', err);
	// Global Express Error Handler
	let status = INTERNAL_SERVER_ERROR;
	let data = {
		message: 'Something went wrong!'
	};
	let summary = null;

	if (!req.path.includes('/api')) {
		return res.status(status).redirect('/error');
	}

	try {
		const parsedData = JSON.parse(err.message);
		status = parsedData.status || status;
		data = parsedData.data || data;
		summary = parsedData.summary || null;
	} catch (e) {
		status = err.status || status;
		data = err.data || err.message;
		summary = err.summary || null;
	}

	res.status(status);

	if (process.env.NODE_ENV === ENVS.DEVELOPMENT) {
		return res.json({
			stacktrace: err.stack,
			data,
			summary
		});
	}

	return res.json({
		data,
		summary
	});
});

db.on('error', () => {
	console.log('Error connecting to MongoDB');
});
db.once('open', () => {
	console.log('Successfully connected to MongoDB');
	return startServer();
});

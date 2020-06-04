const path = require('path');

const v1 = require('./v1/index');

module.exports = (app) => {
	app.use('/api/v1', v1);
	app.use('/*', (req, res) => {
		const filePath = path.resolve(__dirname, '..', 'public', 'assets', 'js', 'dist', 'index.html');
		return res.sendFile(filePath);
	});
};

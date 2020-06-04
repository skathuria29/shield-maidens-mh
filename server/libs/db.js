// Database handler

const mongoose = require('mongoose');
const config = require('config');

const MONGODB_URI = config.get('MONGODB_URI');

mongoose.Promise = Promise;
mongoose.connect(MONGODB_URI, { useNewUrlParser: true });

module.exports = mongoose.connection;

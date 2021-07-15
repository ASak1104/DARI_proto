const express = require('express');
const mongoose = require('mongoose');
const AdminBro = require('admin-bro');
const AdminBroExpress = require('@admin-bro/express');
const AdminBroMongoose = require('@admin-bro/mongoose');
const Admin = require('../schemas/admin');

AdminBro.registerAdapter(AdminBroMongoose)
const adminBro = new AdminBro({
    databases: [mongoose],
});
const adminRouter = AdminBroExpress.buildRouter(adminBro);


const router = express.Router();

router.use(adminRouter)


module.exports = router;
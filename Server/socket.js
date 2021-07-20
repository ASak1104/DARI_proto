const socketIO = require('socket.io');

module.exports = (server, app) => {
    const io = new socketIO.Server({ path: '/socket.io' }).attach(server);
    app.set('io', io);
    io.on('connection', (socket) => {
        const req = socket.request;
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log('Connect the new client', ip, socket.id, req.ip);

        socket.on('disconnect', () => {
            console.log('Disconnect the client', ip, socket.id);
            clearInterval(socket.interval);
        });
        socket.on('error', (error) => {
            console.error(error);
        });
        socket.on('reply', (data) => {
            console.log(data);
        });
        socket.on('join', (data) => {
            console.log('join:', data);
        });
        socket.interval = setInterval(() => {
            socket.emit('news', 'Hello jongsoo!');
        }, 3000);
    });
};
const socketIO = require('socket.io');
const socketioJwt = require('socketio-jwt');


module.exports = (server, app) => {
    const io = new socketIO.Server({ path: '/socket.io' }).attach(server);
    app.set('io', io);

    io.use(socketioJwt.authorize({
        secret: process.env.JWT_SECRET,
        handshake: true,
        // customDecoded: (decoded) => {
        //     return decoded;
        // },
        decodedPropertyName: 'decoded',
    }));

    io.on('connection', (socket) => {
        console.log(`Connect socket user : ${ socket.decoded.userId }`);
        socket.join(socket.decoded._id)
        // console.log(socket.rooms);

        socket.on('disconnect', () => {
            console.log(`Disconnect socket user : ${ socket.decoded.userId }`);
        });
    });
};
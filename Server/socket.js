const socketIO = require('socket.io');

module.exports = (server, app) => {
    const io = new socketIO.Server({ path: '/socket.io' }).attach(server);
    app.set('io', io);
    // const channel = io.of('/channel');
    // const message = io.of('/message');

    io.sockets.on('connection', (socket) => {
        console.log(`Socket connected : ${socket.id}`)

        socket.on('enter', (data) => {
            const roomData = JSON.parse(data)
            const username = roomData.username
            const roomNumber = roomData.roomNumber

            socket.join(`${roomNumber}`)
            console.log(`[Username : ${username}] entered [room number : ${roomNumber}]`)

            const enterData = {
                type: "ENTER",
                content: `${username} entered the room`
            }
            socket.broadcast.to(`${roomNumber}`).emit('update', JSON.stringify(enterData))
        })

        socket.on('left', (data) => {
            const roomData = JSON.parse(data)
            const username = roomData.username
            const roomNumber = roomData.roomNumber

            socket.leave(`${roomNumber}`)
            console.log(`[Username : ${username}] left [room number : ${roomNumber}]`)

            const leftData = {
                type: "LEFT",
                content: `${username} left the room`
            }
            socket.broadcast.to(`${roomNumber}`).emit('update', JSON.stringify(leftData))
        })

        socket.on('newMessage', (data) => {
            const messageData = JSON.parse(data)
            console.log(`[Room Number ${messageData.to}] ${messageData.from} : ${messageData.content}`)
            io.to(`${messageData.to}`).emit('update', JSON.stringify(messageData))
        })
    });
};
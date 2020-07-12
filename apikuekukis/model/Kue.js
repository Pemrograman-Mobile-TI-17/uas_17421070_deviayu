const mongoose = require('mongoose');

const userSchema = mongoose.Schema({

    kodeKue: {
        type: String
    },
    namaKue: {
        type: String
    },
    hargaKue: {
        type: String
    },
    gambar: {
        type: String
    }
})
module.exports = mongoose.model('kue', userSchema)
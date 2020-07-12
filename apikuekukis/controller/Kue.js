const kue = require('../model/Kue.js')
const response = require('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId
exports.inputDataKue = (data, gambar) =>
    new Promise(async (resolve, reject) =>{

        const kueBaru = new kue({
            kodeKue : data.kodeKue,
            namaKue : data.namaKue,
            hargaKue: data.hargaKue,
            gambar: gambar
        })

        await kue.findOne({kodeKue: data.kodeKue})
            .then(kue =>{
                if (kue){
                    reject(response.commonErrorMsg('Kode Kue Sudah Digunakan'))
                }else{
                    kueBaru.save()
                        .then(r =>{
                            resolve(response.commonSuccessMsg('Berhasil Menginput Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf Input Kue Gagal'))
                    })
                }
            }).catch(err =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
            })
    })

exports.lihatDataKue = () =>
    new Promise(async (resolve, reject) =>{
        await kue.find({})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.lihatDetailDataKue = (kodeKue) =>
    new Promise(async (resolve, reject) =>{
        await kue.findOne({kodeKue: kodeKue})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.updateKue = (id, data, gambar) =>
    new Promise(async (resolve, reject)=>{
        await kue.updateOne(
            {_id : ObjectId(id)},
            {
                $set: {
                    kodeKue : data.kodeKue,
                    namaKue : data.namaKue,
                    hargaKue: data.hargaKue,
                    gambar: gambar
                }
            }
        ).then(kue =>{
            resolve(response.commonSuccessMsg('Berhasil Mengubah Data'))
        }).catch(err =>{
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
        })
    })

exports.hapuskue = (_id) =>
    new Promise(async (resolve, reject) =>{
        await kue.remove({_id: ObjectId(_id)})
            .then(() =>{
                resolve(response.commonSuccessMsg('Berhasil Menghapus Data'))
            }).catch(() =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
            })
    })
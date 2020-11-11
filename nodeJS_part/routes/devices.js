const express=require('express');
const router=express.Router();
const mqtt=require('mqtt');
const DHT11=require('../model/dth11');

//mosquitto에 접속
const client=mqtt.connect("mqtt://192.168.0.115");

router.post('/device',function(req,res,next){
    console.log(req.body.sensor);
    if(req.body=='dth11')
    {
       DHT11.find({}).sort({created_at:-1}).limit(10).then(data=>{
            res.send(JSON.stringify(data));
       });
    }
    else{ //yl39 센서

    }
});
module.exports=router;
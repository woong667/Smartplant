const express = require('express')
const mongoose=require('mongoose');
const bodyParser=require('body-parser');
const app = express()
const DHT11=require('./model/dth11');
const YL38=require('./model/yl38');
const mqtt=require('mqtt');
const DeviceRouter=require('./routes/devices');
require('dotenv/config');
const client=mqtt.connect('mqtt://192.168.0.115');  //MQTT와 Connect.
const client2=mqtt.connect('mqtt://192.168.0.115')
////nodejs--->mosquitto로 접속.
app.set("port", "3000");
app.use(bodyParser());
client.on("connect",()=>{
    console.log('MQTT Connected...');
    client.subscribe("dht11");
    client2.subscribe("yl38");
});
client.on("message",(topic,message)=>{
      var obj=JSON.parse(message);
      var date=new Date();
      var year=date.getFullYear();
      var month=date.getMonth();
      var today=date.getDay();
      var hours=date.getHours();
      var minutes=date.getMinutes();
      var seconds=date.getSeconds();
      obj.created_at=new Date(Date.UTC(year,month,today,hours,minutes,seconds));
      console.log(obj);

      const dht11=new DHT11({
          tmp:obj.tmp,
          hum:obj.hum,
          created_at:obj.created_at
      });
    
      try{
      dht11.save(); 
      console.log('insert ok')
      }
      catch(err){
        console.log({message: err});
      }
    
})
client2.on("message",(topic,message)=>{
    var obj=JSON.parse(message);
    var date=new Date();
      var year=date.getFullYear();
      var month=date.getMonth();
      var today=date.getDay();
      var hours=date.getHours();
      var minutes=date.getMinutes();
      var seconds=date.getSeconds();
      obj.created_at=new Date(Date.UTC(year,month,today,hours,minutes,seconds));
      console.log(obj);

      const yl38=new YL38({
        soilhum:obj.soilhum,
        created_at:obj.created_at
    });

    
    try{
    yl38.save(); 
    console.log('insert ok')
    }
    catch(err){
      console.log({message: err});
    }
    
   
})
 
app.get('/', function (req, res) {
  res.send('Main')
})
//app.use('/devices',DeviceRouter);

app.post('/devices/device',(req,res,next)=>{
  
if(req.body.sensor=='dht11'){
 DHT11.find({}).sort({created_at:-1}).limit(10).then(data=>{
   res.send(JSON.stringify(data));
 })
}
else{  //여기는 토양습도센서.
 YL38.find({}).sort({created_at:-1 }).limit(10).then(data=>{
   res.send(JSON.stringify(data));
 })
}
})
  
app.listen(3000,(err)=>{
    if(err)
    return console.log(err);
    console.log("Server is listening on port 3000");
    //Connection DB-->Mongoose 사용
    mongoose.connect(process.env.MONGODB_URL,{
        useNewUrlParser:true,useUnifiedTopology:true
    },()=>console.log('connected to DB')
    );

})

/*mongodb+srv://root:<password>@education.u5v8n.mongodb.net/<dbname>?retryWrites=true&w=majority */

/*education-shard-00-01.u5v8n.mongodb.net:27017 */
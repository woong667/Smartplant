const mongoose=require('mongoose');
const DHT11Schema=mongoose.Schema({


    tmp:{
        type:String,
        require:true
    },
    hum:{
        type:String,
        require:true
    },
    created_at:{
        type:Date,
        dafault:Date.now
    }
});
module.exports=mongoose.model('DHT11',DHT11Schema);
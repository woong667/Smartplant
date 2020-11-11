const mongoose=require('mongoose');
const YL38Schema=mongoose.Schema({


    soilhum:{
        type:String,
        require:true
    },
    created_at:{
        type:Date,
        dafault:Date.now
    }
});
module.exports=mongoose.model('YL38',YL38Schema);
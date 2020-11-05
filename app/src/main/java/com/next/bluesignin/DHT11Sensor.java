package com.next.bluesignin;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DHT11Sensor extends StringRequest {

    String a;
    //URL은 NODEJS에 요청하는 URL
    final static private String URL="http://192.168.0.115:3000/devices/device";
    private Map<String,String> parameters;   //volly 에서는 데이터를 보낼때 해쉬맵을 이용해서 보낸다.
    public DHT11Sensor(String sensors,Response.Listener<String> listener) {
        super(Method.POST,URL, listener,null);  //nodejs에 요청하는 코드
        a=sensors;
    }
    @Override
    protected Map<String,String> getParams(){
        parameters=new HashMap<String,String>();  //volley 에서는 HashMap을 이용해서 보낸다.
        parameters.put("sensor",a);
        return parameters;
    }
}

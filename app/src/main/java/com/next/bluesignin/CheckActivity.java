package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {


    String[] sensors={"dht11","yl39"};
    int p;
    int avgtmp=0,avgsoilhum=0;
    int st,et;
    int sh,eh;
    String detailtmp;
    String detailhum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent intent=getIntent();
        detailtmp=intent.getStringExtra("tmp");
        detailhum=intent.getStringExtra("hum");
        if(detailtmp=="10~15℃")
        {
            st=10;et=15;
        }
        else if(detailtmp=="16~20℃")
        {
            st=16;et=20;
        }
        else if(detailtmp=="21~25℃")
        {
            st=21;et=25;
        }
        else
        {
            st=26;et=30;
        }
        if(detailhum=="항상 흙을 축축하게 유지해야함")
        {
              sh=113;eh=134;
        }
        else if(detailhum=="흙을 촉촉하게 유지해야함(물에 잠기지 않게 주의)")
        {
           sh=103;eh=110;
        }
        else if(detailhum=="토양 표면이 말랐을때 충분히 관수해야함")
        {
            sh=81;eh=102;
        }
        else{
            sh=64;eh=80;
        }



        ArrayAdapter<String> spinnerAdapter=
                new ArrayAdapter<String>(CheckActivity.this,android.R.layout.simple_spinner_dropdown_item,sensors);

        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                final ProgressDialog dialog=new ProgressDialog(CheckActivity.this); //CheckActivity에서 다이얼로그를 띄움
                dialog.setMessage("센서 정보 수집중....");
                dialog.show();
                //여기서부터 nodejs서버와의 데이터를 요청하는 코드 작성.
                p=position;
                Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        if(p==0) {
                            try {
                                JSONArray array = new JSONArray(response);
                                items.clear();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    items.add(new Item(obj.getInt("tmp"),
                                            obj.getInt("hum"),
                                            obj.getString("created_at")));
                                    avgtmp+=obj.getInt("tmp");
                                }//_for
                                avgtmp=avgtmp/(int)array.length();
                                if(avgtmp<st){
                                    Toast.makeText(getApplication(),"화분을 따뜻한곳으로 옮겨주세요", Toast.LENGTH_SHORT).show();
                                }
                                else if(avgtmp>et)
                                {
                                    Toast.makeText(getApplication(),"화분을 시원한곳으로 옮겨주세요", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplication(),"딱 좋은 온도입니다", Toast.LENGTH_SHORT).show();
                                }
                                ItemAdapter adapter = new ItemAdapter(CheckActivity.this);
                                ListView listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);
                                avgtmp=0;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            try {
                                JSONArray array = new JSONArray(response);
                                items2.clear();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    items2.add(new Item2(obj.getInt("soilhum"),
                                            obj.getString("created_at")));
                                    avgsoilhum+=obj.getInt("soilhum");
                                }//_for
                                avgsoilhum=avgsoilhum/(int)array.length();
                                if(avgsoilhum<sh){
                                    Toast.makeText(getApplication(),"물을 좀 줘야해요ㅠㅠ", Toast.LENGTH_SHORT).show();
                                }
                                else if(avgsoilhum>eh)
                                {
                                    Toast.makeText(getApplication(),"당분간 물을 주지마세요ㅠㅠ", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(sh==64&&eh==80)
                                        Toast.makeText(getApplication(),"흙이 말라있습니다.이제 물을 축축히 주세요", Toast.LENGTH_SHORT).show();
                                    else
                                         Toast.makeText(getApplication(),"지금 딱 좋습니다", Toast.LENGTH_SHORT).show();
                                }
                                ItemAdapter2 adapter = new ItemAdapter2(CheckActivity.this);
                                ListView listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);
                                avgsoilhum=0;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    }
                };

                StringRequest dht11 =new DHT11Sensor(sensors[position],listener);
                dht11.setShouldCache(false);  //cache에 있는거 가져오지마라.
                //// 내부적으로 쓰레드방식으로 돈다.
                RequestQueue requestQueue= Volley.newRequestQueue(CheckActivity.this); //queue 안에 데이터가 들어와서 현재 쓰레드와 충돌할 일이없다.
                requestQueue.add(dht11);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class Item{
        int temp, humidity; String created_at;
        Item(int temp, int humidity, String created_at){
            this.temp=temp;
            this.humidity=humidity;
            this.created_at=created_at;
        }
    }

    class Item2{
        int soilhum; String created_at;
        Item2(int soilhum,String created_at){
            this.soilhum=soilhum;
            this.created_at=created_at;
        }
    }

    ArrayList<Item> items=new ArrayList<Item>();
    class ItemAdapter extends ArrayAdapter{
        public ItemAdapter(Context context) {
            super(context, R.layout.dht11_sensor_item, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            if(view==null){
                LayoutInflater inflater=
                        (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.dht11_sensor_item, null);
            }
            TextView tempText=view.findViewById(R.id.tmp);
            TextView humidityText=view.findViewById(R.id.hum);
            TextView createdAtText=view.findViewById(R.id.created_at_dht11);
            tempText.setText("온도:"+items.get(position).temp);
            humidityText.setText("습도:"+items.get(position).humidity);
            createdAtText.setText("수집정보(날짜/시간)"+items.get(position).created_at);
            return view;
        }
    }

    ArrayList<Item2> items2=new ArrayList<Item2>();
    class ItemAdapter2 extends ArrayAdapter{
        public ItemAdapter2(Context context) {
            super(context, R.layout.yl39_sensor_item, items2);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            if(view==null){
                LayoutInflater inflater=
                        (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.yl39_sensor_item, null);
            }
            TextView humidityText=view.findViewById(R.id.soilhum);
            TextView createdAtText=view.findViewById(R.id.created_at_yl39);
            humidityText.setText("토양습도:"+items2.get(position).soilhum);
            createdAtText.setText("수집정보(날짜/시간)"+items2.get(position).created_at);
            return view;
        }
    }
}
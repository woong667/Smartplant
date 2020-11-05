package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


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

                            try {
                                JSONArray array = new JSONArray(response);
                                items.clear();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    items.add(new Item(obj.getInt("tmp"),
                                            obj.getInt("hum"),
                                            obj.getString("created_at")));
                                }//_for
                                ItemAdapter adapter = new ItemAdapter(CheckActivity.this);
                                ListView listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);
                            } catch (Exception e) {
                                e.printStackTrace();
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
}
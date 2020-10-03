package com.next.bluesignin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity {

    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        textView1=(TextView)findViewById(R.id.hi);
        textView2=(TextView)findViewById(R.id.bye);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String name=intent.getStringExtra("name");
        textView1.setText(email);
        textView2.setText(name);


    }
}

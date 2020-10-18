package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{

	TextView Name,Email,PassWord;
	myDBHelper myDBHelper;
	SQLiteDatabase sqlDB;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Button bSignUp = findViewById(R.id.bSignUp);
		TextView tvSignIn = findViewById(R.id.tvSignIn);

		bSignUp.setOnClickListener(this);
		tvSignIn.setOnClickListener(this);

		Name=findViewById(R.id.etUsername);
		Email=findViewById(R.id.etEmail);
		PassWord=findViewById(R.id.etPassword);  //데이터를 받아오고.


		myDBHelper=new myDBHelper(this);       //myDBhelper 인스턴스 생성

		bSignUp.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
                  sqlDB=myDBHelper.getWritableDatabase();   //디비 입력
				  sqlDB.execSQL("INSERT INTO People VALUES('"+Name.getText().toString()+"','"+Email.getText().toString()+"','"+PassWord.getText().toString()+"')");
				  sqlDB.close();
				  Toast.makeText(getApplicationContext(),"가입되었습니다",Toast.LENGTH_SHORT).show();
				  finish();

			}
		});
	}

	static class myDBHelper extends SQLiteOpenHelper{

		public myDBHelper(Context context){
			super(context,"people",null,1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE People(USERNAME VARCHAR(128)PRIMARY KEY,EMAIL VARCHAR(128) NOT NULL,PASSWORD VARCHAR(128) NOT NULL )");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             db.execSQL("DROP TABLE IF EXISTS People");
             onCreate(db);
		}
	}
	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.bSignUp:
				Log.i(MainActivity.LOG_TAG, "Sign up clicked.");
				break;
			case R.id.tvSignIn:
				finish();
				break;
		}
	}
}

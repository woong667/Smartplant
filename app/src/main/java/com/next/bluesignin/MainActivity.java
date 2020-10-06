package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	public static String LOG_TAG = "BlueSignIn";

	String Cemail,Cpassword,Cname;
	TextView name,email,password;
	SQLiteDatabase database;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bSignIn = findViewById(R.id.bSignIn);    //signIn 가져왓씀
		TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
		TextView tvSignUp = findViewById(R.id.tvSignUp);
		email=findViewById(R.id.etEmail);
		password=findViewById(R.id.etPassword);
		SignUpActivity.myDBHelper databaseHelper=new SignUpActivity.myDBHelper(this);
		database=databaseHelper.getReadableDatabase();

		bSignIn.setOnClickListener(this);
		tvForgotPassword.setOnClickListener(this);
		tvSignUp.setOnClickListener(this);

		bSignIn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				if(database!=null){

					Cursor cursor = database.rawQuery("SELECT email,password,username FROM people;", null);
					int count = cursor.getCount(); // 이 코드가 있어야 등록된 정보들을 전부 불러올 수 있다
					String text1=email.getText().toString();
					String text2=password.getText().toString();

					for(int i=0;i<count;i++) {       // 있습니다. 이 코드만 추가해주시면 가입한 모든 아이디

						cursor.moveToNext();     // 로 로그인이 가능해요!!
						Cemail = cursor.getString(0);
						Cpassword = cursor.getString(1);
						Cname=cursor.getString(2);


						if (text1.equals(Cemail) && text2.equals(Cpassword)) {

							//Toast.makeText(getApplication(), Cname+"님 환영합니다.", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(MainActivity.this, MainActivity2.class);
							intent.putExtra("email",Cemail);
							intent.putExtra("password",Cpassword);
							intent.putExtra("name",Cname);
							startActivity(intent);
							Toast.makeText(getApplication(), Cname + "님 환영합니다.", Toast.LENGTH_SHORT).show();
							finish();
							break;
						}
                      if(i==count-1)
					  {
						  Toast.makeText(getApplication(),"이메일 혹은 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
					  }
					}
					cursor.close();
				}
				else{
					Toast.makeText(getApplicationContext(),"회원이 아직 없는데요..",Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.bSignIn:
				Log.i(LOG_TAG, "Sign in clicked.");
				break;
			case R.id.tvForgotPassword:
				startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
				break;
			case R.id.tvSignUp:
				startActivity(new Intent(MainActivity.this, SignUpActivity.class));
				break;
		}
	}
}

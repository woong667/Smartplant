package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DetailPageActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;
    UploadActivity.myDBHelper database;
    SQLiteDatabase sqlDB;
    private ImageView img1;
    TextView t1,t2,t3,t4;
    Button btn;
    Bitmap bitm;
    String email;
    Uri uri;
    String Cplantphoto,Cplantname,Cdes,Ctemparature,Chumid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        database=new UploadActivity.myDBHelper(this);
        sqlDB=database.getReadableDatabase();
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        int seq=intent.getIntExtra("seq",0);
        Cursor cursor = sqlDB.rawQuery("SELECT plantphoto,plantname,des,temparature,humid FROM user_plant where user=?;",new String[]{email});
        cursor.moveToPosition(seq);
        Cplantphoto=cursor.getString(0);
        //uri=Uri.parse(Cplantphoto);
        Cplantname=cursor.getString(1);
        Cdes=cursor.getString(2);
        Ctemparature=cursor.getString(3);
        Chumid=cursor.getString(4);
        t1=(TextView)findViewById(R.id.DetailPlantName);
        t2=(TextView)findViewById(R.id.DetailDes);
        t3=(TextView)findViewById(R.id.DetailTmp);
        t4=(TextView)findViewById(R.id.DetailHum);
        img1=(ImageView)findViewById(R.id.DetailImage);
        btn=(Button)findViewById(R.id.checkplant);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailPageActivity.this,CheckActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("tmp",t3.getText().toString());
                intent.putExtra("hum",t4.getText().toString());
                startActivity(intent);

            }
        });

       // File file = new File(uri.getPath());
        //Uri contentUri = getImageContentUri(this, file.getAbsolutePath());
        t1.setText(Cplantname);
        t2.setText(Cdes);
        t3.setText(Ctemparature);
        t4.setText(Chumid);
       /* String fileName=Cplantphoto;
        File file=new File(fileName);
        bitm=BitmapFactory.decodeFile(file.getAbsolutePath());
        Toast.makeText(getApplicationContext(),bitm+"",Toast.LENGTH_SHORT).show();
        img1.setImageBitmap(bitm);*/
        img1.setImageResource(R.drawable.d);
    }


    public void setImage(Uri uri) throws FileNotFoundException {
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            img1.setImageBitmap(bitmap);

    }

}
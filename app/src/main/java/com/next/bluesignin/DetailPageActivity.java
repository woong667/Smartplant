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
    Uri uri;
    String Cplantphoto,Cplantname,Cdes,Ctemparature,Chumid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        database=new UploadActivity.myDBHelper(this);
        sqlDB=database.getReadableDatabase();
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
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
        Toast.makeText(getApplicationContext(),uri+"",Toast.LENGTH_SHORT).show();
       // File file = new File(uri.getPath());
        //Uri contentUri = getImageContentUri(this, file.getAbsolutePath());
        t1.setText(Cplantname);
        t2.setText(Cdes);
        t3.setText(Ctemparature);
        t4.setText(Chumid);
        img1.setImageResource(R.drawable.d);
    }


    public void setImage(Uri uri) throws FileNotFoundException {
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            img1.setImageBitmap(bitmap);

    }


  /* public static Uri getImageContentUri(Context context, String absPath) {
        Cursor cursor = context.getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI , new String[] { MediaStore.Images.Media._ID } , MediaStore.Images.Media.DATA + "=? " , new String[] { absPath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , Integer.toString(id));
        } else if (!absPath.isEmpty()) {
            ContentValues values = new ContentValues(); values.put(MediaStore.Images.Media.DATA, absPath);
            return context.getContentResolver().insert( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values); }
        else { return null; }
    }*/




}
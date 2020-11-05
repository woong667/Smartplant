package com.next.bluesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_CODE = 1;
    ImageView imageView;
    SearchView searchView;
    String email,Ctemparature,Chumid;
    AutoCompleteTextView planttype;
    TextView plantname,des;
    myDBHelper myDBHelper;
    SQLiteDatabase sqlDB,sqlDB2;
    MainActivity2.myDBHelper database;
    Uri uri;
    private static final String[] COUNTRIES = new String[] {
            "가울테리아", "개운죽", "윌마", "공작야자", "관엽베고니아",
            "관음죽", "구문초", "구즈마니아", "군자란", "글레코마",
            "금목서","금사철나무","금식나무","금전수","금천죽",
            "기누라","꽃베고니아","나도풍란","나한송","남천",
            "네마탄투스","네오레겔리아","녹영","뉴기니아봉선화","대만고무나무",
            "더피고사리","덕구리난","데코라고무나무","덴파레","도깨비고비",
            "돈나무","동백","둥근잎 아랄리아","듀란타","송오브자마이카",
            "와네끼","송오브인디아","자바","콤팩타","트리컬러 레인보우",
            "드라세나 드라코","드라세나 마지나타","드라세나 맛상게아나","드라세나 산데리아나","세레스",
            "수르쿨로사","디지고데카","마리안느","트로픽스노우","떡갈잎 고무나무",
            "러브체인","렉스베고니아","루모라고사리","루스커스","마란타 류코뉴라",
            "마삭줄","만년청","만데빌라","멕시코소철","멜라니 고무나무",
            "목베고니아","몬스테라","무늬관음죽","무늬마삭줄","무늬벤자민고무나무",
            "무늬산호수","무늬석창포","홍콩","무늬알피니아","무늬유카",
            "무늬접란","무늬털머위","무늬팻츠헤대라","무늬푸밀라고무나무","뮤렌베키아",
            "바위치","박쥐란","반딧불털머위","백량금","백정화",
            "백화등","벤자민고무나무","킹","벵갈고무나무","병솔나무",
            "보스톤고사리","봉의꼬리","부겐빌레아","브룬펠시아","브리세아",
            "비젯티접란","비타툼접란","산세베리아","골든하니","산호수",
            "삼색데코라고무나무","후마타","새우란","석창포","세네시오 라디칸스",
            "세이프릿지 야자","셀라기넬라","소철","소피아 고무나무","솔레이롤리아",
            "수박페페로미아","수박필레아","수염 틸란드시아","숙근이베리스","쉐플레라 홍콩",
            "스킨답서스","스파티필룸","시서스","시클라멘","심비디움",
            "싱고니움","아글라오네마","아데니움","아디안텀","아라우카리아",
            "아레카야자","아마릴리스","아스파라거스 풀루모수스","아스플레니움","아왜나무",
            "아이비","아펠란드라","아프리칸 바이올렛","안수리움","알로카시아 아마조니카",
            "알로카시아 쿠쿨라타","얼룩자주달개비","에메랄드리플 페페로미아","에크메아 파시아타","엘라티올 베고니아",
            "여우꼬리풀","엽란","옥살리스","온시디움","왜란",
            "왜성종려죽","움벨라타 고무나무","유카","은사철나무","익소라",
            "인삼벤자민","자금우","자란","자주색만년초","접란",
            "제라니움","좀마삭줄","종려방동사니","종려죽","죽백나무",
            "줄리아 페페로미아","참쇠고비","치자나무","칼라데아 마코야나","칼라데아 인시그니스",
            "칼라데아 크로카타","칼라디움","칼랑코에","커피나무","켄챠야자",
            "코르딜리네 레드에지","크로톤","크립탄서스","클레마티스","털달개비",
            "털머위","테이블야자","톨미아","틸란드시아","파키라",
            "파피오페딜럼","팔레놉시스","팔손이나무","팬더 고무나무","팻츠헤대라",
            "페페로미아 오브투시폴리아","페페로미아 클루시폴리아","페페로미아 푸테올라타","포인세티아","폴리시아스",
            "푸밀라 고무나무","프테리스","피라칸사","피토니아 핑크스타","피토니아 화이트스타","필투라툼 접란","플레아 글라우카","선라이트","제나두","필로덴드론 고엘디",
            "필로덴드론 레몬라임","펠로덴드론 셀로움","펠로덴드론 옥시카르디움","필로덴드론 콩고","해마리아",
            "행운목","헤미오니티스","해피트리","협죽도","형관스킨답서스",
            "호야","호야 엑소티카","호주매","홀리아페페로미아","황금마삭줄",
            "황금죽","후피향나무","흰꽃나도사프란","흰줄무늬달개비","히포에스테스"


    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        imageView=(ImageView)findViewById(R.id.imagebtn);
        imageView.setOnClickListener(this);
        Intent intent = getIntent();
        email=intent.getStringExtra("email");   //이메일 받아왔음 데이터베이스에 이거 집어넣을거다
        Button btn=(Button)findViewById(R.id.btn_upload);  //업로드 버튼
        planttype=findViewById(R.id.search_plant);   //식물 종류
        plantname=findViewById(R.id.plant_name);    //화분 이름
        des=findViewById(R.id.des);                 //화분 설명

        database=new MainActivity2.myDBHelper(this);
        //////////////데이터베이스 추가/////////////////////////
        myDBHelper=new myDBHelper(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.search_plant);
        textView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqlDB=database.getWritableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT TEMPARATURE,HUMID FROM plant where plantname=?;",new String[]{planttype.getText().toString()});
                int count=cursor.getCount();
                cursor.moveToNext();
                Ctemparature=cursor.getString(0); //온도 얻고
                Chumid=cursor.getString(1);       //습도 얻고*/
                String temparature,humid;
                if(Ctemparature=="082001")
                    temparature="10~15℃";
                else if(Ctemparature=="082002")
                    temparature="16~20℃";
                else if(Ctemparature=="082003")
                    temparature="21~25℃";
                else
                    temparature="26~30℃";

                if(Chumid=="053001")
                    humid="항상 흙을 축축하게 유지해야함";
                else if(Chumid=="053002")
                    humid="흙을 촉촉하게 유지해야함(물에 잠기지 않게 주의)";
                else if(Chumid=="053003")
                    humid="토양 표면이 말랐을때 충분히 관수해야함";
                else
                    humid="화분 흙 대부분이 말랐을때 충분히 관수해야함";

                sqlDB2=myDBHelper.getWritableDatabase();
                String filepath=getRealPathFromURI(uri);
                sqlDB2.execSQL("INSERT INTO USER_PLANT VALUES('"+email+"','"+planttype.getText().toString()+"','"+filepath+"','"+plantname.getText().toString()+"','"+des.getText().toString()+"','"+temparature+"','"+humid+"')");
                sqlDB.close();
                sqlDB2.close();
                Intent intent = new Intent(UploadActivity.this, MainActivity2.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();

            }
        });




    }

    private String getRealPathFromURI(Uri contentURI) {



        String result;

        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);



        if (cursor == null) { // Source is Dropbox or other similar local file path

            result = contentURI.getPath();



        } else {

            cursor.moveToFirst();

            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

            result = cursor.getString(idx);

            cursor.close();

        }



        return result;

    }






    public void onClickButton1(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_CODE) {
            uri = data.getData();
        }
        setImage(uri); //그리고 이미지를 선택했다면 그 이미지의 uri을 가져옵니다.  SQLite에는 그냥 string형으로 저장하면 됨...ㄳ

    }
    private void setImage(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        onClickButton1(view);

    }


    static class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context){
            super(context,"user_plant",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user_plant(USER VARCHAR(128) NOT NULL,PLANTTYPE VARCHAR(128) NOT NULL,PLANTPHOTO VARCHAR(255) NOT NULL,PLANTNAME VARCHAR(128) NOT NULL,DES VARCHAR(255) NOT NULL,TEMPARATURE VARCHAR(128) NOT NULL,HUMID VARCHAR(128) NOT NULL )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS USER_PLANT");
            onCreate(db);
        }
    }
}
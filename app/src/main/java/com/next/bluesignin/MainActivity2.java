package com.next.bluesignin;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity //implements NavigationView.OnNavigationItemSelectedListener
{



    String email; //이메일로 데이터베이스 저장했으니....
    myDBHelper myDBHelper;
    SQLiteDatabase sqlDB,sqldata;
    private RecyclerAdapter adapter;
    UploadActivity.myDBHelper database;
    String planturi;
    String Cplanttype,Cplantname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        email=intent.getStringExtra("email");
        FloatingActionButton fab = findViewById(R.id.aa);
        database=new UploadActivity.myDBHelper(this);  //database로 이제 접근가능.
        sqldata=database.getReadableDatabase();

        if(database!=null) {
            //recycleView//
            init();
            getData();
            //reCycleView//
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //그 아래에 둥둥떠다니는 버튼
                Intent intent=new Intent(MainActivity2.this,UploadActivity.class);
                intent.putExtra("email",email);   //이메일을 보내준다.
                startActivity(intent);
            }
        });

        myDBHelper=new myDBHelper(this);
        //////////////데이터베이스 추가/////////////////////////
        sqlDB=myDBHelper.getWritableDatabase();   //테이블을 생성. 아직 데이터는 없음.
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM plant;", null);
        int count=cursor.getCount();
        //여기서부터
        if(count==0) //아직 아무 데이터도 없으면
        {
            //여기서부터 이제 식물들 insert.....정녕 노가다말고 답이 없는것인가..
            sqlDB.execSQL("INSERT INTO plant VALUES('가울테리아','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('개운죽','082002','053001')");
            sqlDB.execSQL("INSERT INTO plant VALUES('윌마','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('공작야자','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('관엽베고니아','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('관음죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('구문초','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('구즈마니아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('군자란','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('글레코마','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('금목서','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('금사철나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('금식나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('금전수','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('금천죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('기누라','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('꽃베고니아','082003','053001')");
            sqlDB.execSQL("INSERT INTO plant VALUES('나도풍란','082003','053004')");
            sqlDB.execSQL("INSERT INTO plant VALUES('나한송','082001','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('남천','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('네마탄투스','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('네오레겔리아','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('녹영','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('뉴기니아봉선화','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('대만고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('더피고사리','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('덕구리난','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('데코라고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('덴파레','082003','053004')");
            sqlDB.execSQL("INSERT INTO plant VALUES('도깨비고비','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('돈나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('동백','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('둥근잎 아랄리아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('듀란타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('송오브자마이카','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('와네끼','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('송오브인디아','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('자바','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('콤팩타','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('트리컬러 레인보우','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('드라코','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('마지나타','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('맛상게아나','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('산데리아나','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('세레스','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('수르쿨로사','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('디지고데카','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('마리안느','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('트로픽스노우','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('떡갈잎 고무나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('러브체인','082002','053004')");
            sqlDB.execSQL("INSERT INTO plant VALUES('렉스베고니아','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('루모라고사리','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('루스커스','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('마란타 류코뉴라','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('마삭줄','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('만년청','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('만데빌라','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('멕시코소철','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('멜라니 고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('목베고니아','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('몬스테라','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬관음죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬마삭줄','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬벤자민고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬산호수','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬석창포','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬쉐플레라','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬알피니아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬유카','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬접란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬털머위','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬팻츠헤데라','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('무늬푸밀라 고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('뮤렌베키아','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('바위치','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('박쥐란','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('반딧불털머위','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('백량금','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('백정화','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('백화등','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('벤자민고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('벤자민고무나무 킹','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('벵갈고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('병솔나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('보스톤고사리','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('봉의꼬리','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('부겐빌레아','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('브룬펠시아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('브리세아','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('비젯티접란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('비타툼접란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('산세베리아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('골든하니','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('산호수','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('삼색데코라고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('후마타','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('새우란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('석창포','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('세네시오 라디칸스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('세이프릿지 야자','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('셀라기넬라','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('소철','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('소피아 고무나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('솔레이롤리아','082002','053004')");
            sqlDB.execSQL("INSERT INTO plant VALUES('수박페페로미아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('수박필레아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('수염 틸란드시아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('숙근이베리스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('홍콩','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('스킨답서스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('스파티필룸','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('스파티필룸 광엽','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('시서스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('시클라멘','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('심비디움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('싱고니움','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아글라오네마','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아데니움','082003','053004')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아디안텀','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아라우카리아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아레카야자','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아마릴리스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('풀루모수스','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아스플레니움','082003','053001')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아왜나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아이비','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아펠란드라','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('아프리칸 바이올렛','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('안수리움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('알로카시아 아마조니카','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('알로카시아 쿠쿨라타','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('얼룩자주달개비','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('에메랄드리플 페페로미아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('에크메아 파시아타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('엘라티올 베고니아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('여우꼬리풀','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('엽란','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('옥살리스','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('온시디움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('왜란','082001','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('왜성종려죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('움벨라타 고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('유카','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('은사철나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('익소라','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('인삼벤자민','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('자금우','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('자란','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('자주색만년초','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('접란','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('제라니움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('좀마삭줄','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('종려방동사니','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('종려죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('죽백나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('줄리아 페페로미아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('참쇠고비','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('치자나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('칼라데아 마코야나','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('칼라데아 인시그니스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('칼라데아 크로카타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('칼라디움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('칼랑코에','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('커피나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('켄챠야자','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('코르딜리네 레드에지','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('크로톤','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('크립탄서스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('클레마티스','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('털달개비','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('털머위','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('테이블야자','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('톨미아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('틸란드시아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('파키라','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('파피오페딜럼','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('호접란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('팔손이나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('팬더 고무나무','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('팻츠헤데라','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('페페로미아 오브투시폴리아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('페페로미아 클루시폴리아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('페페로미아 푸테올라타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('포인세티아','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('폴리시아스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('푸밀라고무나무','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('프테리스','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('피라칸사','082002','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('피토니아 핑크스타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('피토니아 화이트스타','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('픽투라툼 접란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필레아 글라우카','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('선라이트','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('제나두','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필로덴드론 고엘디','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필로덴드론 레몬라임','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필로덴드론 셀로움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필로덴드론 옥시카르디움','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('필로덴드론 콩고','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('해마리아','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('행운목','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('헤미오니티스','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('해피트리','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('협죽도','082003','053002')");
            sqlDB.execSQL("INSERT INTO plant VALUES('형광스킨답서스','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('호야','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('호야 엑소티카','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('호주매','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('홀리아 페페로미아','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('황금마삭줄','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('황금죽','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('후파향나무','082002','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('흰꽃나도사프란','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('흰줄무늬달개비','082003','053003')");
            sqlDB.execSQL("INSERT INTO plant VALUES('히포에스테스','082003','053002')");
            sqlDB.close();
        }
        //여기까지 데이터 입력



    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        //리사이클뷰에 올릴 데이터 얻는곳.


        List<String> listTitle=new ArrayList<>();
        List<String> listContent=new ArrayList<>();
        List<String> listResId= new ArrayList<>();

        //셋 다 String


        Cursor cursor = sqldata.rawQuery("SELECT planttype,plantphoto,plantname FROM user_plant where user=?;",new String[]{email});
        int count=cursor.getCount();

        for(int i=0;i<count;i++){

            cursor.moveToNext();
            Cplanttype=cursor.getString(0);
            planturi=cursor.getString(1);
            Cplantname=cursor.getString(2);
            listTitle.add(Cplanttype);
            listResId.add(planturi);
            listContent.add(Cplantname);
        }


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            RecyclerAdapter.Data data = new RecyclerAdapter.Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }


        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    static class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context){
            super(context,"plant",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE plant(PLANTNAME VARCHAR(128)PRIMARY KEY,TEMPARATURE VARCHAR(128) NOT NULL,HUMID VARCHAR(128) NOT NULL )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Plant");
            onCreate(db);
        }
    }



}

package com.example.mrhjs.ocha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Ootd extends AppCompatActivity {
    Bitmap obitmap;
    Bitmap tbitmap;
    Bitmap pbitmap;
    Bitmap sbitmap;
    Bitmap abitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ootd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView overcoat = findViewById(R.id.overcoat);
        ImageView top = findViewById(R.id.top);
        ImageView pants = findViewById(R.id.pants);
        final ImageView shoes = findViewById(R.id.shoes);
        ImageView acc = findViewById(R.id.acc);
        final String overcoat_url = "https://images.yoox.com/41/41705980vv_12_f.jpg";
        final String top_url = "http://t-sketch.net/wp-content/uploads/2016/08/Men-%EA%B7%B8%EB%A0%88%EC%9D%B4.png";
        final String pants_url = "http://www.topten10.co.kr/data/productimage/a/3/MSY4PP1502DNP.jpg";
        final String shoes_url = "http://gdimg.gmarket.co.kr/1163017131/still/600?ver=1526001095";
        final String acc_url = "https://www.costco.co.kr/medias/sys_master/images/hc3/h2b/10678401040414.jpg";

        Thread ootdThread = new Thread() {

            @Override
            public void run() {
                try {
                    URL ourl = new URL(overcoat_url);
                    URL turl = new URL(top_url);
                    URL purl = new URL(pants_url);
                    URL surl = new URL(shoes_url);
                    URL aurl = new URL(acc_url);
                    // web에서 이미지 가져온 뒤 imgeview에 지정할 bitmap 만듬
                    HttpURLConnection oconn = (HttpURLConnection) ourl.openConnection();
                    oconn.setDoInput(true); // 서버로부터 응답 수신
                    oconn.connect();
                    HttpURLConnection tconn = (HttpURLConnection) turl.openConnection();
                    tconn.setDoInput(true);
                    tconn.connect();
                    HttpURLConnection pconn = (HttpURLConnection) purl.openConnection();
                    pconn.setDoInput(true);
                    pconn.connect();
                    HttpURLConnection sconn = (HttpURLConnection) surl.openConnection();
                    sconn.setDoInput(true);
                    sconn.connect();
                    HttpURLConnection aconn = (HttpURLConnection) aurl.openConnection();
                    aconn.setDoInput(true);
                    aconn.connect();

                    InputStream ois = oconn.getInputStream();
                    obitmap = BitmapFactory.decodeStream(ois);
                    InputStream tis = tconn.getInputStream();
                    tbitmap = BitmapFactory.decodeStream(tis);
                    InputStream pis = pconn.getInputStream();
                    pbitmap = BitmapFactory.decodeStream(pis);
                    InputStream sis = sconn.getInputStream();
                    sbitmap = BitmapFactory.decodeStream(sis);
                    InputStream ais = aconn.getInputStream();
                    abitmap = BitmapFactory.decodeStream(ais);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        ootdThread.start();

        try {
            ootdThread.join();
            overcoat.setImageBitmap(obitmap);
            top.setImageBitmap(tbitmap);
            pants.setImageBitmap(pbitmap);
            shoes.setImageBitmap(sbitmap);
            acc.setImageBitmap(abitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.mrhjs.ocha;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class Ootd extends AppCompatActivity {
    Bitmap obitmap;
    Bitmap tbitmap;
    Bitmap pbitmap;
    Bitmap sbitmap;
    Bitmap abitmap;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ootd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.setTitle("Outfit Of The Day");
        Intent intent = getIntent();

        ImageButton overcoat = findViewById(R.id.overcoat);

        ImageButton top = findViewById(R.id.top);
        ImageButton pants = findViewById(R.id.pants);
        ImageButton shoes = findViewById(R.id.shoes);
        ImageButton acc = findViewById(R.id.acc);
        final ArrayList<String> url = intent.getExtras().getStringArrayList("url");
        final ArrayList<String> shop_url = intent.getExtras().getStringArrayList("shop_url");
        //Toast.makeText(Ootd.this, url.get(0)+url.get(1)+url.get(2)url.get(3)+url.get(0), Toast.LENGTH_SHORT).show();
        Log.i("url0",url.get(0));
        Log.i("url1",url.get(1));
        Log.i("url2",url.get(2));
        Log.i("url3",url.get(3));
        Log.i("url4",url.get(4));
        try{
        URL ourl = new URL(url.get(0));
        String orl = ourl.toString();
        URL turl = new URL(url.get(1));
        String trl = turl.toString();
        URL purl = new URL(url.get(2));
        String prl = purl.toString();
        URL surl = new URL(url.get(3));
        String srl = surl.toString();
        URL aurl = new URL(url.get(4));
        String arl = aurl.toString();
        Picasso.get().load(orl).into(overcoat);
        Picasso.get().load(trl).into(top);
        Picasso.get().load(prl).into(pants);
        Picasso.get().load(srl).into(shoes);
        Picasso.get().load(arl).into(acc);
        }
        catch(Exception e) {
            Log.i("error", e.toString());
        }
//        Thread ootdThread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL ourl = new URL(url.get(0));
//                    Log.i("ourl", ourl.toString());
//                    URL turl = new URL(url.get(1));
//                    URL purl = new URL(url.get(2));
//                    URL surl = new URL(url.get(3));
//                    URL aurl = new URL(url.get(4));
//                    // web에서 이미지 가져온 뒤 imgeview에 지정할 bitmap 만듬
//                    HttpURLConnection oconn = (HttpURLConnection) ourl.openConnection();
//                    oconn.setDoInput(true); // 서버로부터 응답 수신
//
//                    oconn.connect();
///*
//                    HttpURLConnection tconn = (HttpURLConnection) turl.openConnection();
//                    tconn.setDoInput(true);
//                    tconn.connect();
//                    HttpURLConnection pconn = (HttpURLConnection) purl.openConnection();
//                    pconn.setDoInput(true);
//                    pconn.connect();
//                    HttpURLConnection sconn = (HttpURLConnection) surl.openConnection();
//                    sconn.setDoInput(true);
//                    sconn.connect();
//                    HttpURLConnection aconn = (HttpURLConnection) aurl.openConnection();
//                    aconn.setDoInput(true);
//                    aconn.connect();
//*/
//                    InputStream ois = oconn.getInputStream();
//                    obitmap = BitmapFactory.decodeStream(ois);
//                 /*   InputStream tis = tconn.getInputStream();
//                    tbitmap = BitmapFactory.decodeStream(tis);
//                    InputStream pis = pconn.getInputStream();
//                    pbitmap = BitmapFactory.decodeStream(pis);
//                    InputStream sis = sconn.getInputStream();
//                    sbitmap = BitmapFactory.decodeStream(sis);
//                    InputStream ais = aconn.getInputStream();
//                    abitmap = BitmapFactory.decodeStream(ais);
//*/
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        ootdThread.start();
//
//        try {
//            ootdThread.join();
//            overcoat.setImageBitmap(obitmap);
//            top.setImageBitmap(tbitmap);
//            pants.setImageBitmap(pbitmap);
//            shoes.setImageBitmap(sbitmap);
//            acc.setImageBitmap(abitmap);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        overcoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Ootd.this, Webview.class);
                intent.putExtra("shop_url", shop_url.get(0));
                startActivity(intent);
            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Ootd.this, Webview.class);
                intent.putExtra("shop_url", shop_url.get(1));
                startActivity(intent);
            }
        });
        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Ootd.this, Webview.class);
                intent.putExtra("shop_url", shop_url.get(2));
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Ootd.this, Webview.class);
                intent.putExtra("shop_url", shop_url.get(3));
                startActivity(intent);
            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Ootd.this, Webview.class);
                intent.putExtra("shop_url", shop_url.get(4));
                startActivity(intent);
            }
        });
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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}

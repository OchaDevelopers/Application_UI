package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class Ootd extends AppCompatActivity {
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
        }

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
}

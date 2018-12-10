package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main extends AppCompatActivity {
    NetworkService networkService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        networkService = ApplicationController.getInstance().getNetworkService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton signin = (ImageButton) findViewById(R.id.signin);
        ImageButton signup = (ImageButton) findViewById(R.id.signup);
        Button findpass = (Button) findViewById(R.id.find);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
            }
        });
        //데이터 통신 예제!!!
       /* signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                Call<Data> loginCall = networkService.newContent(data);
                loginCall.enqueue(new Callback<Data>() {
                      @Override
                      public void onResponse(Call<Data> call, Response<Data> response) {
                          Toast.makeText(Main.this, "hello", Toast.LENGTH_SHORT).show();
                      }
                      @Override
                      public void onFailure(Call<Data> call, Throwable t) {

                      }
                  });

            }
        });*/
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Signup.class);
                startActivity(intent);
            }
        });
        findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Find_password.class);
                startActivity(intent);
            }
        });
    }
}

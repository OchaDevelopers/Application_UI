package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    NetworkService networkService;
    EditText id;
    EditText pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        networkService = ApplicationController.getInstance().getNetworkService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton signin = (ImageButton) findViewById(R.id.signin);
        id = findViewById(R.id.id);
        pw = findViewById(R.id.password);
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Signin_data odata = new Signin_data();
                odata.id = id.getText().toString();
                odata.pw = pw.getText().toString();
                Call<Signin_data> loginCall = networkService.newContentin(odata);
                loginCall.enqueue(new Callback<Signin_data>() {
                    @Override
                    public void onResponse(Call<Signin_data> call, Response<Signin_data> response) {
                        if (response.body().id.equals(odata.id)) {
                            Toast.makeText(Login.this, "success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<Signin_data> call, Throwable t) {
                        Toast.makeText(Login.this, "Check your network", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

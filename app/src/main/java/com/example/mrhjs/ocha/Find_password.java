package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Find_password extends AppCompatActivity {
    NetworkService networkService;
    EditText f_id;
    EditText f_answer;
    TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        networkService = ApplicationController.getInstance().getNetworkService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        f_id=findViewById(R.id.id);
        f_answer=findViewById(R.id.answer);
        password=findViewById(R.id.password);
        Button find = (Button) findViewById(R.id.find);
        Button done = (Button) findViewById(R.id.done);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Find_Data data = new Find_Data();
                data.f_id = f_id.getText().toString();
                data.f_answer=f_answer.getText().toString();
                data.f_password="a";
                Call<Find_Data> loginCall = networkService.newContentfind(data);
                loginCall.enqueue(new Callback<Find_Data>() {
                    @Override
                    public void onResponse(Call<Find_Data> call, Response<Find_Data> response) {
                        if (response.body().f_id.equals(data.f_id)) {
                            Toast.makeText(Find_password.this, response.body().getF_password(), Toast.LENGTH_SHORT).show();
                            password.setText(response.body().getF_password());
                        } else {
                            Toast.makeText(Find_password.this, "Check your information", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Find_Data> call, Throwable t) {

                    }
                });

            }
        });
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_password.this, Main.class);
                startActivity(intent);
            }

        });
    }
}

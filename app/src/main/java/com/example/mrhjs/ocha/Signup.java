package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {
    NetworkService networkService;
    EditText id;
    EditText password;
    Spinner question;
    EditText answer;
    EditText email;
    RadioButton man;
    RadioButton woman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        networkService = ApplicationController.getInstance().getNetworkService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ImageButton createaccount = (ImageButton) findViewById(R.id.createacc);
        id = findViewById(R.id.id);
        password=findViewById(R.id.password);
        question= findViewById(R.id.question);
        answer=findViewById(R.id.answer);
        email=findViewById(R.id.email);
        man=findViewById(R.id.manbutton);
        woman=findViewById(R.id.womanbutton);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Signup_data data = new Signup_data();
                data.id = id.getText().toString();
                data.password=password.getText().toString();
                data.password_question=question.getSelectedItem().toString();
                data.answer=answer.getText().toString();
                data.email=email.getText().toString();
                if (man.isChecked() == true) {
                    data.gender = 0;
                } else {
                    data.gender = 1;
                }
                Call<Signup_data> loginCall = networkService.newContent(data);
                loginCall.enqueue(new Callback<Signup_data>() {
                    @Override
                    public void onResponse(Call<Signup_data> call, Response<Signup_data> response) {

                        response.body();
                        Log.i("response", response.body().toString());
                        if (response.body().getId().equals(data.id)) {
                            Toast.makeText(Signup.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, Main.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Signup.this, "Please check your information", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<Signup_data> call, Throwable t) {
                        Toast.makeText(Signup.this, "Please check your information", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        Spinner s = (Spinner)findViewById(R.id.question);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
}

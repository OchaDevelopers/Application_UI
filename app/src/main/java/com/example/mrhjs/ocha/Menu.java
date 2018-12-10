package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {
    NetworkService networkService;
    String temperature = "";
    String weather_description = "";
    public ArrayList<String> url = new ArrayList<>();
    public ArrayList<String> shop_url = new ArrayList<>();
    public ArrayList<String> color = new ArrayList<>();
    public ArrayList<String> pattern = new ArrayList<>();
    public ArrayList<String> texture = new ArrayList<>();
    String recommend_o;
    String recommend_t;
    String recommend_p;
    String recommend_s;
    String recommend_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        networkService = ApplicationController.getInstance().getNetworkService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageButton ootd = (ImageButton) findViewById(R.id.ootd);
        ImageButton chatbot = (ImageButton) findViewById(R.id.chatbot);
        ImageButton alarm = (ImageButton) findViewById(R.id.alarm);
        ImageButton preference = (ImageButton) findViewById(R.id.preference);
        getWeatherData(37.30, 126.97);
        int c = 0, p = 0, t = 0, tag = 0;
        if (getIntent().hasExtra("color")) {
            color = getIntent().getExtras().getStringArrayList("color");
            tag = 1;
        }
        if (getIntent().hasExtra("pattern")) {
            pattern = getIntent().getExtras().getStringArrayList("pattern");
        }
        if (getIntent().hasExtra("texture")) {
            texture = getIntent().getExtras().getStringArrayList("texture");
        }
        c = color.size();
        p = pattern.size();
        t = texture.size();

        recommend_o = "M, -3C, , coat, Black,,";
        recommend_t = "M, -3C, , top, white,,";
        recommend_p = "M, -3C, , pants, blue,,";
        recommend_s = "M, -3C, , shoes, white,,";
        recommend_a = "M, -3C, snow , scarf, black,,";
        if (tag == 1) {
            int rand = (int) (Math.random() * 2) + 1;
            if (rand == 1) {
                recommend_o = "M, -3C, , padding, Black,,";
            } else {
                recommend_o = "M, -3C, , coat, Black,,";
            }
            rand = (int) (Math.random() * c);
            int randp = (int) (Math.random() * p);
            int randt = (int) (Math.random() * t);
            recommend_t = "M, -3C, , top, "+color.get(rand)+","+pattern.get(randp)+","+texture.get(randt)+"";
            rand = (int) (Math.random() * 2) + 1;
            if (rand == 1) {
                recommend_p = "M, -3C, , pants, brown, cotton,";
            } else {
                recommend_p = "M, -3C, , pants, blue, denim,";
            }
            rand = (int) (Math.random() * 2) + 1;
            if (rand == 1) {
                recommend_s = "M, -3C, , shoes, black,,";
            } else {
                recommend_s = "M, -3C, , shoes, white,,";
            }
            recommend_a = "M, -3C, , scarf, black,,";
            if (weather_description.contains("snow")) {
                recommend_a = "M, -3C, , umbrella, black,,";
            }
        }
        Recommand_data data_o = new Recommand_data();
        recommand(recommend_o, 0);

        ootd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Ootd.class);
                intent.putExtra("url", url);
                intent.putExtra("shop_url",shop_url);
                startActivity(intent);
            }
        });
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Chatbot.class);
                intent.putExtra("url", url);
                intent.putExtra("shop_url",shop_url);
                intent.putExtra("temperature", temperature);
                intent.putExtra("description", weather_description);
                startActivity(intent);
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Alarm.class);
                startActivity(intent);
            }
        });
        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Preference.class);
                startActivity(intent);
            }
        });



    }
    public void recommand(String keyword, final int count) {
        Recommand_data data_o = new Recommand_data();
        data_o.keyword = keyword;
        Toast.makeText(Menu.this, keyword, Toast.LENGTH_SHORT).show();
        Call<Recommand_data> loginCall_function = networkService.newContentre(data_o);
        loginCall_function.enqueue(new Callback<Recommand_data>() {
            @Override
            public void onResponse(Call<Recommand_data> call, Response<Recommand_data> response) {
                url.add(response.body().keyword.split(",")[0]);
                shop_url.add(response.body().keyword.split(",")[1]);
                int count_temp;
                count_temp = count;
                count_temp++;
                if (count_temp == 1) {
                    recommand(recommend_t, count_temp);
                } else if (count_temp == 2) {
                    recommand(recommend_p, count_temp);
                } else if (count_temp == 3) {
                    recommand(recommend_s, count_temp);
                } else if (count_temp == 4) {
                    recommand(recommend_a, count_temp);
                }
                Log.i("recommend:", url.get(0));
            }

            @Override
            public void onFailure(Call<Recommand_data> call, Throwable t) {
                Toast.makeText(Menu.this, "Check your recommando", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getWeatherData(double lat, double lng) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lng+"&appid=43735c18b195ded966ff4a6ed49f106e&units=metric";
        //String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lng+"&units=metric&appid=43735c18b195ded966ff4a6ed49f106e";
        ReceiveWeatherTask receiveUseTask = new ReceiveWeatherTask();
        receiveUseTask.execute(url);
    }

    private class ReceiveWeatherTask extends AsyncTask<String,Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... datas) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(datas[0]).openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader in = new BufferedReader(reader);

                    String readed;
                    while ((readed = in.readLine()) != null) {
                        JSONObject jObject = new JSONObject(readed);
                        return jObject;
                    }
                } else {
                    return null;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private static final String TAG = "MainActivity";

        @Override
        protected void onPostExecute(JSONObject result) {
            if (result == null) {
                Toast.makeText(getApplicationContext(), "JSONObject is null", Toast.LENGTH_LONG).show();
                return;
            }
            WeatherInfo info = new WeatherInfo(result);
            temperature = info.nowTemp[1];
            weather_description = info.weatherMain[1];

            //Toast.makeText(getApplicationContext(), Integer.toString((int)Double.parseDouble(temperature)), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), weather_code, Toast.LENGTH_SHORT).show();
            String getData = info.ToString();
            //Toast.makeText(getApplicationContext(), getData, Toast.LENGTH_LONG).show();
            info.getMaxTemp();
        }
    }
    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    public ArrayList<String> getShop_url() {
        return shop_url;
    }

    public void setShop_url(ArrayList<String> shop_url) {
        this.shop_url = shop_url;
    }
}

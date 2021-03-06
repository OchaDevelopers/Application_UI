package com.example.mrhjs.ocha;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kangcholmin on 20/01/2018.
 */

public class ApplicationController extends Application {

    /**
     * Application 클래스를 상속받은 ApplicationController 객체는 어플리케이션에서 단 하나만 존재해야 합니다.
     * 따라서 내부에 ApplicationController 형의 instance를 만들어준 후
     * getter를 통해 자신의 instance를 가져오는 겁니다.
     */

    public int user_id;
    private String token;
    private String deviceid;


    private static ApplicationController instance;

    public static ApplicationController getInstance() {
        return instance;
    }

    //통신할 서버의 주소입니다. 클라이언트는 이 주소에 query 또는 path 등을 추가하여 요청합니다.
    private static final String baseUrl = "http://54.186.60.112:3006";

    //NetworkService도 마찬가지로 Application을 상속받은 ApplicationController 내에서 관리해주는 것이 좋습니다.
    private NetworkService networkService;

    public NetworkService getNetworkService() {
        return networkService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        this.buildService();
    }
    private void buildService() {
        synchronized (ApplicationController.class)

        {
            {
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();
                GsonConverterFactory factory = GsonConverterFactory.create(gson);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .build();

                //retrofit.create(NetworkService.class)를 통해 새로운 NetworkService를 만들어줍니다.
                networkService = retrofit.create(NetworkService.class);

            }
        }
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        if(token == null || token.equals("")) return "";
        return token;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}

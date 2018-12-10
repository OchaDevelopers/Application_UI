package com.example.mrhjs.ocha;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ai.api.AIServiceException;
import ai.api.RequestExtras;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.GsonFactory;
import ai.api.model.AIContext;
import ai.api.model.AIError;
import ai.api.model.AIEvent;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;

public class Chatbot extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = Chatbot.class.getName();
    private Gson gson = GsonFactory.getGson();
    private AIDataService aiDataService;
    private ChatView chatView;
    private User myAccount;
    private User OChaBot;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mrhjs.ocha.R.layout.activity_chatbot);
        Toolbar ctoolbar = (Toolbar) findViewById(R.id.chatbot_toolbar);
        setSupportActionBar(ctoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.setTitle("Chatbot");
        initChatView();

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        //Language, Dialogflow Client access token
        final LanguageConfig config = new LanguageConfig("ko", "66a24cbd72294f60bdc08c8be00c49a8");
        initService(config);
        if (getIntent().getExtras().getString("alarm") != null) {
            final Message receivedMessage = new Message.Builder()
                    .setUser(OChaBot)
                    .setRightMessage(false)
                    .setMessageText(getIntent().getExtras().getString("alarm"))
                    .build();
            chatView.receive(receivedMessage);
            ttsGreater21(receivedMessage.getMessageText());
        } else{

        }

    }

    @Override
    public void onClick(View v) {
        //new message
        final Message message = new Message.Builder()
                .setUser(myAccount)
                .setRightMessage(true)
                .setMessageText(chatView.getInputText())
                .hideIcon(true)
                .build();
        //Set to chat view
        chatView.send(message);
        sendRequest(chatView.getInputText());
        //Reset edit text
        chatView.setInputText("");
    }

    /*
     * AIRequest should have query OR event
     */
    private void sendRequest(String text) {
        final String queryString = String.valueOf(text);
        final String eventString = null;
        final String contextString = null;

        if (TextUtils.isEmpty(queryString) && TextUtils.isEmpty(eventString)) {
            //onError(new AIError(getString(com.example.mrhjs.ocha.R.string.non_empty_query)));
            return;
        }
        String temp = getIntent().getExtras().getString("temperature");
        String desc = getIntent().getExtras().getString("description");
        String speak;
        double temperature = Double.parseDouble(temp);
        int rand = (int) (Math.random() + 2) + 1;
        if (temperature < 0) {
            if (rand == 1) {
                speak = "오늘은 날씨가 많이 춥습니다. 따뜻하게 입고가세요.";
            } else {
                speak = "밖이 정말 추워요! 두꺼운 외투를 걸치고 가세요";
            }
        } else {
            if (rand == 1) {
                speak = "오늘은 날씨가 조금 풀린 것 같아요.";
            } else {
                speak = "날이 조금 풀렸어요. 눈이 내릴 정도는 아니라네요!";
            }
        }
        ArrayList<String> url = getIntent().getExtras().getStringArrayList("url");
        ArrayList<String> shop_url = getIntent().getExtras().getStringArrayList("shop_url");
        if (text.contains("온도")) {
            final Message receivedMessage = new Message.Builder()
                    .setUser(OChaBot)
                    .setRightMessage(false)
                    .setMessageText(speak)
                    .build();
            chatView.receive(receivedMessage);
            ttsGreater21(receivedMessage.getMessageText());
            ttsGreater21(receivedMessage.getMessageText());
        } else if (text.contains("날씨")) {
            final Message receivedMessage = new Message.Builder()
                    .setUser(OChaBot)
                    .setRightMessage(false)
                    .setMessageText("오늘의 날씨는 " + desc + "입니다.")
                    .build();
            chatView.receive(receivedMessage);
            ttsGreater21(receivedMessage.getMessageText());
        } else if (text.contains("추천")) {
            Intent intent = new Intent(Chatbot.this, Ootd.class);
            intent.putExtra("url", url);
            intent.putExtra("shop_url", shop_url);
            startActivity(intent);
        } else if (text.contains("쇼핑")) {
            Intent intent = new Intent(Chatbot.this, Webview.class);
            intent.putExtra("shop_url", "https://shopping.naver.com/home/p/index.nhn");
            startActivity(intent);

        }else {
            new AiTask().execute(queryString, eventString, contextString);
        }

    }

    public class AiTask extends AsyncTask<String, Void, AIResponse> {
        private AIError aiError;

        @Override
        protected AIResponse doInBackground(final String... params) {
            final AIRequest request = new AIRequest();
            String query = params[0];
            String event = params[1];
            String context = params[2];

            if (!TextUtils.isEmpty(query)){
                request.setQuery(query);
            }

            if (!TextUtils.isEmpty(event)){
                request.setEvent(new AIEvent(event));
            }

            RequestExtras requestExtras = null;
            if (!TextUtils.isEmpty(context)) {
                final List<AIContext> contexts = Collections.singletonList(new AIContext(context));
                requestExtras = new RequestExtras(contexts, null);
            }

            try {
                return aiDataService.request(request, requestExtras);
            } catch (final AIServiceException e) {
                aiError = new AIError(e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(final AIResponse response) {
            if (response != null) {
                onResult(response);
            } else {
                onError(aiError);
            }
        }
    }


    private void onResult(final AIResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Variables
                gson.toJson(response);
                final Status status = response.getStatus();
                final Result result = response.getResult();
                final String speech = result.getFulfillment().getSpeech();
                final Metadata metadata = result.getMetadata();
                final HashMap<String, JsonElement> params = result.getParameters();

                if (metadata != null) {
                }

                if (params != null && !params.isEmpty()) {

                    for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                    }
                }

                //Update view to bot says
                final Message receivedMessage = new Message.Builder()
                        .setUser(OChaBot)
                        .setRightMessage(false)
                        .setMessageText(speech)
                        .build();
                chatView.receive(receivedMessage);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(receivedMessage.getMessageText());
                } else {
                    ttsUnder20(receivedMessage.getMessageText());
                }
            }
        });
    }

    private void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void initChatView() {
        int myId = 0;
        Bitmap icon = BitmapFactory.decodeResource(getResources(), com.example.mrhjs.ocha.R.drawable.ic_action_user);
        String myName = "User";
        myAccount = new User(myId, myName, icon);

        int botId = 1;
        String botName = "O!Cha";
        OChaBot = new User(botId, botName, icon);

        chatView = findViewById(com.example.mrhjs.ocha.R.id.chat_view);
        chatView.setRightBubbleColor(ContextCompat.getColor(this, com.example.mrhjs.ocha.R.color.green500));
        chatView.setLeftBubbleColor(Color.WHITE);
        chatView.setBackgroundColor(ContextCompat.getColor(this, com.example.mrhjs.ocha.R.color.blueGray500));
        chatView.setSendButtonColor(ContextCompat.getColor(this, com.example.mrhjs.ocha.R.color.lightBlue500));
        chatView.setSendIcon(com.example.mrhjs.ocha.R.drawable.ic_action_send);
        chatView.setRightMessageTextColor(Color.WHITE);
        chatView.setLeftMessageTextColor(Color.BLACK);
        chatView.setUsernameTextColor(Color.WHITE);
        chatView.setSendTimeTextColor(Color.WHITE);
        chatView.setDateSeparatorColor(Color.WHITE);
        chatView.setInputTextHint("new message...");
        chatView.setMessageMarginTop(5);
        chatView.setMessageMarginBottom(5);
        chatView.setOnClickSendButtonListener(this);
    }

    private void initService(final LanguageConfig languageConfig) {
        final AIConfiguration.SupportedLanguages lang =
                AIConfiguration.SupportedLanguages.fromLanguageTag(languageConfig.getLanguageCode());
        final AIConfiguration config = new AIConfiguration(languageConfig.getAccessToken(),
                lang,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(this, config);
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
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
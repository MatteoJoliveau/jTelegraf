package com.matteojoliveau.jtelegraf.core;

import com.google.gson.Gson;
import com.matteojoliveau.jtelegraf.core.config.TelegramInfo;
import com.matteojoliveau.jtelegraf.core.types.TelegramResultObj;
import com.matteojoliveau.jtelegraf.core.types.Update;
import com.matteojoliveau.jtelegraf.core.types.User;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Telegram {
    private final Gson gson;
    private String token;
    private OkHttpClient httpClient;
    private final String APIENDPOINT;

    @Autowired
    public Telegram(TelegramInfo info, OkHttpClient httpClient, Gson gson) {
        this.token = info.getToken();
        this.httpClient = httpClient;
        this.gson = gson;
        APIENDPOINT =  String.format("https://api.telegram.org/bot%s/", token);
    }


    public User getMe() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(APIENDPOINT + "getMe").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Response res = null;
        try {
            res = makeRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = null;
        if (res != null && res.body().contentType().equals(MediaType.parse("application/json"))) try {
            user = gson.fromJson(res.body().string(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Update getUpdates() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(APIENDPOINT + "getUpdates").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Response res = null;
        try {
            res = makeRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Update[] updates = null;
        if (res != null && res.body().contentType().equals(MediaType.parse("application/json"))) try {
            String string = res.body().string();
            TelegramResultObj t = gson.fromJson(string, TelegramResultObj.class);
            updates = (Update[]) t.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updates[0];
    }

    private Response makeRequest(Request request) throws IOException {
        return httpClient.newCall(request).execute();
    }

}

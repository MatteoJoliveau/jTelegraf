package com.matteojoliveau.jtelegraf.telegram.api;

import com.matteojoliveau.jtelegraf.telegram.api.types.*;
import com.matteojoliveau.jtelegraf.telegram.api.types.results.ChatResultObject;
import com.matteojoliveau.jtelegraf.telegram.api.types.results.UpdateResultObject;
import com.matteojoliveau.jtelegraf.telegram.api.types.results.UserResultObject;
import okhttp3.*;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.IOException;
import java.util.List;

public class Telegram {
    private final ObjectMapper objectMapper;
    private OkHttpClient httpClient;
    private final String APIENDPOINT;

    public Telegram(String token, OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        APIENDPOINT = String.format("https://api.telegram.org/bot%s/", token);
    }


    public User getMe() {
        String url = HttpUrl.parse(APIENDPOINT + "getMe").newBuilder().build().toString();
        Request request = new Request.Builder().url(url).build();
        Response res = null;
        try {
            res = makeRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = null;
        if (res != null && res.body().contentType().equals(MediaType.parse("application/json"))) try {
            String body = res.body().string();
            user = objectMapper.readValue(body, UserResultObject.class).getUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Update> getUpdates() {
        String url = HttpUrl.parse(APIENDPOINT + "getUpdates").newBuilder().build().toString();
        Request request = new Request.Builder().url(url).build();
        Response res = null;
        try {
            res = makeRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Update> updates = null;
        if (res != null && res.body().contentType().equals(MediaType.parse("application/json"))) try {
            String body = res.body().string();
            updates = objectMapper.readValue(body, UpdateResultObject.class).getUpdates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updates;
    }

    public Chat getChat(String chatId) {
        String url = HttpUrl.parse(APIENDPOINT + "getChat?chat_id=" + chatId).newBuilder().build().toString();
        Request request = new Request.Builder().url(url).build();
        Response res = null;
        try {
            res = makeRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Chat chat = null;
        if (res != null && res.body().contentType().equals(MediaType.parse("application/json"))) try {
            String body = res.body().string();
            chat = objectMapper.readValue(body, ChatResultObject.class).getChat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chat;
    }

    private Response makeRequest(Request request) throws IOException {
        return httpClient.newCall(request).execute();
    }

}

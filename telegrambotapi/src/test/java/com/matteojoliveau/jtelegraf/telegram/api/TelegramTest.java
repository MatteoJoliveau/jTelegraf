package com.matteojoliveau.jtelegraf.telegram.api;

import okhttp3.OkHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class TelegramTest {
    @Test
    public void name() throws Exception {
        Telegram telegram = new Telegram("286303429:AAEZvX73ABB2o9zwBaUE2m_8HMLu9IctvHY", new OkHttpClient(), new ObjectMapper());
//        telegram.getMe();
        telegram.getUpdates();
//        telegram.getChat("25591608");
    }

}
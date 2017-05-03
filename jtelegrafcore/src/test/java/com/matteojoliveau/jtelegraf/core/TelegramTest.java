package com.matteojoliveau.jtelegraf.core;

import com.google.gson.Gson;
import com.matteojoliveau.jtelegraf.core.config.TelegramInfo;
import okhttp3.OkHttpClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelegramTest {

    @Test
    public void name() throws Exception {
        Telegram telegram = new Telegram(new TelegramInfo("286303429:AAEZvX73ABB2o9zwBaUE2m_8HMLu9IctvHY"), new OkHttpClient(), new Gson());
        telegram.getUpdates();
    }
}
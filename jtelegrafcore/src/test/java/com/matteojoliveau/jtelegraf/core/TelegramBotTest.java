package com.matteojoliveau.jtelegraf.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class TelegramBotTest {

    @Test
    public void name() throws Exception {
        TelegramBot bot = new TelegramBot("286303429:AAEZvX73ABB2o9zwBaUE2m_8HMLu9IctvHY");
        bot.on("text", (ctx) -> {
            ctx.reply("ciao");
        });

        bot.command("salve", ctx -> ctx.reply("Salve!"));

        bot.startPolling();
    }


}
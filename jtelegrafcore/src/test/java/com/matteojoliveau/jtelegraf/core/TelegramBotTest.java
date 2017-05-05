package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.types.CallbackQuery;
import com.matteojoliveau.telegram.api.types.markups.Button;
import com.matteojoliveau.telegram.api.types.markups.InlineKeyboardButton;
import com.matteojoliveau.telegram.api.types.markups.InlineKeyboardMarkup;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TelegramBotTest {

    @Test
    public void name() throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
        TelegramBot bot = new TelegramBot("286303429:AAEZvX73ABB2o9zwBaUE2m_8HMLu9IctvHY");
        bot.on("text", (ctx) -> {
            ctx.reply("ciao");
        });

        bot.command("salve", ctx -> ctx.reply("Salve!"));

        bot.command("start", ctx -> {
            InlineKeyboardButton button1 = new InlineKeyboardButton("Ciao!");
            InlineKeyboardButton button2 = new InlineKeyboardButton("Addio");
            button1.setCallbackData("ciao");
            button2.setCallbackData("addio");
            List<List<Button>> keyboard = Collections.singletonList(Arrays.asList(button1, button2));
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup(keyboard);
            ctx.reply("Prova i pulsanti!", markup);
        });

        bot.on("callback_query", ctx -> {
            CallbackQuery callbackQuery = ctx.callbackQuery();
            ctx.reply("Hai scelto: " + callbackQuery.getData());
        });

        bot.startPolling();
    }


}
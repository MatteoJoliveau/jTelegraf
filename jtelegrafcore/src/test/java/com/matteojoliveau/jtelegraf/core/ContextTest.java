package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.jtelegraf.core.config.TelegramInfo;
import com.matteojoliveau.jtelegraf.telegram.api.types.Message;
import com.matteojoliveau.jtelegraf.telegram.api.types.Update;
import com.matteojoliveau.jtelegraf.telegram.api.types.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContextTest {

    private TelegramInfo info;

    @Before
    public void setUp() throws Exception {
        info = new TelegramInfo("286303429:AAEZvX73ABB2o9zwBaUE2m_8HMLu9IctvHY");
    }

    @Test
    public void getMe() throws Exception {
        Context ctx = new Context(info, new Update());
        User me = ctx.me();
        System.out.println(me);
    }

    @Test
    public void getUser() throws Exception {
        Message message = new Message.Builder().id(1L).user(new User.Builder().id(1L).firstName("Matteo").username("gamescodex").build()).build();
        Update update = new Update(1L, message);
        Context ctx = new Context(info, update);
        User from = ctx.from();
        System.out.println(from);
    }
}
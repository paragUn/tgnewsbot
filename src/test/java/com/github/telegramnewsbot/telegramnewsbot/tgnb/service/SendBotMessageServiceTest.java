package com.github.telegramnewsbot.telegramnewsbot.tgnb.service;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.bot.RedirectTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private RedirectTelegramBot redirectTelegramBot;

    @BeforeEach
    public void init() {
        redirectTelegramBot = Mockito.mock(RedirectTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(redirectTelegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        sendBotMessageService.sendMessage(chatId, message);

        Mockito.verify(redirectTelegramBot).execute(sendMessage);
    }
}

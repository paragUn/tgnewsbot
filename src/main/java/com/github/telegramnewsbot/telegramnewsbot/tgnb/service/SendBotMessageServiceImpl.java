package com.github.telegramnewsbot.telegramnewsbot.tgnb.service;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.bot.RedirectTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */

public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final RedirectTelegramBot redirectTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(RedirectTelegramBot redirectTelegramBot){
        this.redirectTelegramBot = redirectTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            redirectTelegramBot.execute(sendMessage);
        } catch (TelegramApiException telegramApiException){
            telegramApiException.printStackTrace();
        }
    }
}

package com.github.telegramnewsbot.telegramnewsbot.tgnb.command;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    private static final String START_MESSAGE = "Привет. Я Redirect Telegram Bot. Я помогу тебе получать в одном месте посты " +
            "из каналов, которые тебе интересны. Я еще маленький и только учусь.";

    public StartCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}

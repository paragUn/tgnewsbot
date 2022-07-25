package com.github.telegramnewsbot.telegramnewsbot.tgnb.command;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.repository.entity.TelegramUser;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotMessageService;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;



/**
 * Start {@link Command}.
 */

public class StartCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String START_MESSAGE = "Привет. Я Redirect Telegram Bot. Я помогу тебе получать в одном месте посты " +
            "из каналов, которые тебе интересны. Я еще маленький и только учусь.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService){
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}

package com.github.telegramnewsbot.telegramnewsbot.tgnb.bot;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.command.CommandContainer;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotServiceMessageImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.telegramnewsbot.telegramnewsbot.tgnb.command.CommandName.NO;

/**
 * TelegramBot for community from community.
 */
@Component
public class RedirectTelegramBot extends TelegramLongPollingBot{

    public static String COMMAND_PREFIX ="/";
    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;
    private final CommandContainer commandContainer;
    public RedirectTelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotServiceMessageImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if(message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}

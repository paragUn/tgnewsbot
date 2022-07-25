package com.github.telegramnewsbot.telegramnewsbot.tgnb.command;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.telegramnewsbot.telegramnewsbot.tgnb.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨ <b>Доступные команды</b>✨\n\n"

                    + "<b>Начать\\Закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - получить статистику\n\n", START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), STAT.getCommandName());
    public HelpCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}

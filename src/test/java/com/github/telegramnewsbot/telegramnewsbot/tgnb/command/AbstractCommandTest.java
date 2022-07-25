package com.github.telegramnewsbot.telegramnewsbot.tgnb.command;

import com.github.telegramnewsbot.telegramnewsbot.tgnb.bot.RedirectTelegramBot;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotMessageService;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.SendBotMessageServiceImpl;
import com.github.telegramnewsbot.telegramnewsbot.tgnb.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link Command}s.
 */

abstract class AbstractCommandTest {
        protected RedirectTelegramBot redirectTelegramBot = Mockito.mock(RedirectTelegramBot.class);
        protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(redirectTelegramBot);

        abstract String getCommandName();

        abstract String getCommandMessage();

        abstract Command getCommand();

        @Test
        public void shouldProperlyExecuteCommand() throws TelegramApiException {
                //given
                Long chatId = 1234567824356L;

                Update update = new Update();
                Message message = Mockito.mock(Message.class);
                Mockito.when(message.getChatId()).thenReturn(chatId);
                Mockito.when(message.getText()).thenReturn(getCommandName());
                update.setMessage(message);

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId.toString());
                sendMessage.setText(getCommandMessage());
                sendMessage.enableHtml(true);

                //when
                getCommand().execute(update);

                //then
                Mockito.verify(redirectTelegramBot).execute(sendMessage);
        }
}

package com.meleschenya.tourist_telegram_bot.bot;

import com.meleschenya.tourist_telegram_bot.bot.bot_utils.BotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    /*
    City utils: using for creation message (SendMessage obj.)
     */
    @Autowired
    private BotUtils cityUtils;

    /*
    Bot name read from resources/application.yaml for connection to Telegram Bot
     */
    @Value("${bot.name}")
    private String botUserName;

    /*
    Bot token read from resources/application.yaml for connection to Telegram Bot
     */
    @Value("${bot.token}")
    private String botToken;

    /*
    Overriding of method from TelegramLongPollingBot for processing message from users
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message msg = update.getMessage();
            long chatId = msg.getChatId();
            if (msg.hasText()) {
                SendMessage message = cityUtils.createCityMessage(msg.getText(), chatId);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message msg = callbackQuery.getMessage();
            long chatId = msg.getChatId();
            try {
                execute(new SendMessage().setText(callbackQuery.getData()).setChatId(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}



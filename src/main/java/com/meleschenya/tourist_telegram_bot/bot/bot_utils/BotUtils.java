package com.meleschenya.tourist_telegram_bot.bot.bot_utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BotUtils {

    public SendMessage createTextMessage(String text, long chatId);

    public SendMessage createCityMessage(String cityName, long chatId);

}

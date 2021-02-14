package com.meleschenya.tourist_telegram_bot.bot.bot_utils.impl;

import com.meleschenya.tourist_telegram_bot.bot.bot_utils.BotUtils;
import com.meleschenya.tourist_telegram_bot.entity.City;
import com.meleschenya.tourist_telegram_bot.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityUtils implements BotUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityUtils.class);

    /*
    Message read from resources/application.yaml for sending in Telegram as response
     */
    @Value("${botMessage.cityNotFound}")
    private String cityNotFoundMessage;

    /*
    Message read from resources/application.yaml for sending in Telegram as response
     */
    @Value("${botMessage.multiplyCities}")
    private String multiplyCitiesFound;

    /*
    City service: using for getting city and his description from database
     */
    @Autowired
    private CityService cityService;

    /*
    Method for creation buttons, when city service return more than 1
     */
    private SendMessage createLotsOfCitiesMessage(long chatId, List<City> cityList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        cityList.stream().forEach(x -> {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(x.getName() + " (" + x.getCountry() + ")");
            inlineKeyboardButton.setCallbackData(x.getDescription());
            keyboardButtonsRow.add(inlineKeyboardButton);
        });
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText(multiplyCitiesFound).setReplyMarkup(inlineKeyboardMarkup);
    }

    /*
    Just create SendMessage obj. with text and chatId
     */
    @Override
    public SendMessage createTextMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setText(text).setChatId(chatId);

        return message;
    }

    /*
    Creation SendMessage with body consist of:
            City, if search in DB return 1 City
            List of Buttons, if search in DB return more then 1 City
     */
    public SendMessage createCityMessage(String cityName, long chatId) {
        List<City> citiesByNameList = cityService.getCityByName(cityName);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        LOGGER.info("Message created for chatId: " + chatId);
        int listSize = citiesByNameList.size();
        if (listSize == 1) {
            message.setText(citiesByNameList.get(0).getDescription());
            LOGGER.info("Return result for 1 element");
            return message;
        }
        if (listSize > 1) {
            LOGGER.info("Return result for " + listSize + " element");
            return createLotsOfCitiesMessage(chatId, citiesByNameList);
        }
        message.setText(cityNotFoundMessage);
        LOGGER.info("Not found cities.");

        return message;
    }
}

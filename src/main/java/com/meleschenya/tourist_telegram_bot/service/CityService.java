package com.meleschenya.tourist_telegram_bot.service;

import com.meleschenya.tourist_telegram_bot.entity.City;

import java.util.List;

public interface CityService {

    void deleteById(Integer id);

    City saveAndFlush(City city);

    public void updateCity(String name, String description, String country, Integer id);

    List<City> findAll();

    City getCityById(Integer id);

    List<City> getCityByName(String name);

}

package com.meleschenya.tourist_telegram_bot.service.impl;

import com.meleschenya.tourist_telegram_bot.entity.City;
import com.meleschenya.tourist_telegram_bot.repository.CityRepository;
import com.meleschenya.tourist_telegram_bot.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CItyServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City getCityById(Integer id) {
        return repository.getCityById(id);
    }

    @Override
    public List<City> getCityByName(String name) {
        return repository.getCityByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public City saveAndFlush(City city) {
        repository.saveAndFlush(city);
        City savedCity = getCityById(city.getId());
        return savedCity;
    }

    @Override
    public void updateCity(String name, String description, String country, Integer id) {
        repository.updateCity(name, description, country, id);
    }

}

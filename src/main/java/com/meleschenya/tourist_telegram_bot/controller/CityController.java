package com.meleschenya.tourist_telegram_bot.controller;

import com.meleschenya.tourist_telegram_bot.entity.City;
import com.meleschenya.tourist_telegram_bot.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
Rest controller for working with DB for CRUD operation with CIty
 */
@RestController
@RequestMapping("/")
public class CityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);
    @Autowired
    private CityService cityService;

    @GetMapping("/delete/{id}")
    public void deleteCityById(@PathVariable Integer id) {
        LOGGER.info("Object with ID: " + id + " was deleted.");
        cityService.deleteById(id);
    }

    @PostMapping(path = "/addCity", consumes = "application/json", produces = "application/json")
    public City addEmployee(@RequestBody City city) {
        LOGGER.info("Object \n" + city.toString() + " \nwas sawed.");

        return cityService.saveAndFlush(city);
    }

    @PostMapping(path = "/updateCity", consumes = "application/json", produces = "application/json")
    public void updateCity(@RequestBody City city) {
        LOGGER.info("Object with ID: " + city.getId() + " was updated on \n" + city.toString());
        cityService.updateCity(city.getName(), city.getDescription(), city.getCountry(), city.getId());
    }

    @PostMapping(path = "/updateCityById/{id}", consumes = "application/json", produces = "application/json")
    public void updateCityById(@RequestBody City city, @PathVariable Integer id) {
        LOGGER.info("Object with ID: " + city.getId() + " was updated on \n" + city.toString());
        cityService.updateCity(city.getName(), city.getDescription(), city.getCountry(), id);
    }

    @GetMapping(path = "/getAllCities")
    private List<City> getAllCities() {
        LOGGER.info("All objects were returned.");

        return cityService.findAll();
    }

    @GetMapping(path = "/getCityById/{id}")
    private City getCityById(@PathVariable Integer id) {
        LOGGER.info("Object with ID: " + id + " was returned.");

        return cityService.getCityById(id);
    }
}

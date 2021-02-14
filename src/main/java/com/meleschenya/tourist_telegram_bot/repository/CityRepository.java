package com.meleschenya.tourist_telegram_bot.repository;

import com.meleschenya.tourist_telegram_bot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
Class-component for getting result from DB with using JPA
 */
@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    @Modifying
    @Query("delete from City c where c.id =:id")
    void deleteById(@Param("id") Integer id);


    City saveAndFlush(City city);


    @Modifying
    @Query("update City c set c.name =:name, c.description =:description, c.country =:country where c.id =:id")
    public void updateCity(@Param("name") String name, @Param("description") String description,
                           @Param("country") String country, @Param("id") Integer id);


    List<City> findAll();


    @Query("select c from City c where c.id =:id")
    City getCityById(@Param("id") Integer id);


    @Query("select c from City c where c.name =:name")
    List<City> getCityByName(@Param("name") String name);


}

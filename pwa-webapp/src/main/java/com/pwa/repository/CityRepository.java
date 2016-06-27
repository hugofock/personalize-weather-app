package com.pwa.repository;

import com.pwa.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends BaseEntityRepository<City> {

    @Query("SELECT c FROM City c WHERE c.name = :cityName")
    City findCityByCityName(@Param("cityName") String cityName);

}


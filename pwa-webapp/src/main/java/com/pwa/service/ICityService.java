package com.pwa.service;

import com.pwa.model.City;

import java.util.List;

public interface ICityService extends BaseService {

    List<City> findAllCity();

    City findCityByCityName(String cityName);

}

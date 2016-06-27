package com.pwa.service.impl;

import com.pwa.model.City;
import com.pwa.repository.CityRepository;
import com.pwa.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CityService implements ICityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City findCityByCityName(String cityName) {
        return cityRepository.findCityByCityName(cityName);
    }

}

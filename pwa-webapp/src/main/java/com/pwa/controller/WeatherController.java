package com.pwa.controller;

import com.pwa.common.constant.MimeType;
import com.pwa.common.constant.Status;
import com.pwa.common.exception.UnexpectedException;
import com.pwa.common.util.ObjectUtil;
import com.pwa.common.vo.BasicResponseEntityVO;
import com.pwa.model.City;
import com.pwa.model.weather.WeatherInfo;
import com.pwa.service.ICityService;
import com.pwa.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/weather")
public class WeatherController extends BaseController {

    private ICityService cityService;

    private IWeatherService weatherService;

    @Autowired
    public WeatherController(IWeatherService weatherService, ICityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/weatherInfo/{cityName}", method = { RequestMethod.GET }, produces = MimeType.JSON)
    public ResponseEntity<BasicResponseEntityVO> weatherInfo(@PathVariable("cityName") String cityName) {
        BasicResponseEntityVO<WeatherInfo> basicResponseEntityVO = new BasicResponseEntityVO<>();
        City city = cityService.findCityByCityName(cityName);
        if (city == null) {
            basicResponseEntityVO.setStatus(Status.FAILURE);
            basicResponseEntityVO.setMessage("Invalid Weather Info Requested");
            return new ResponseEntity<BasicResponseEntityVO>(basicResponseEntityVO, HttpStatus.OK);
        } else {

            try {

                WeatherInfo weatherInfo = weatherService.findWeather(city.getSearchId());

                System.out.println("weatherInfo = " + ObjectUtil.toJson(weatherInfo));
                basicResponseEntityVO.setStatus(Status.SUCCESS);
                basicResponseEntityVO.setObject(weatherInfo);

            } catch (UnexpectedException ex) {
                LOGGER.error(ex.getMessage(), ex);

                basicResponseEntityVO.setStatus(Status.FAILURE);
                basicResponseEntityVO.setMessage("Invalid Weather Info Requested");
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);

                basicResponseEntityVO.setStatus(Status.FAILURE);
                basicResponseEntityVO.setMessage("Unrecovered exception error");

            } finally {
                return new ResponseEntity<BasicResponseEntityVO>(basicResponseEntityVO, HttpStatus.OK);
            }

        }

    }

    @RequestMapping(value = "/findAllCity", method = { RequestMethod.GET }, produces = MimeType.JSON)
    public ResponseEntity<BasicResponseEntityVO> findAllCity() {
        BasicResponseEntityVO<List<City>> basicResponseEntityVO = new BasicResponseEntityVO<>();
        List<City> city = cityService.findAllCity();
        if (city.isEmpty()) {
            basicResponseEntityVO.setStatus(Status.FAILURE);
            basicResponseEntityVO.setMessage("Invalid Weather Info Requested");
        } else {
            basicResponseEntityVO.setObject(city);
            basicResponseEntityVO.setStatus(Status.SUCCESS);
        }
        return new ResponseEntity<BasicResponseEntityVO>(basicResponseEntityVO, HttpStatus.OK);

    }

}

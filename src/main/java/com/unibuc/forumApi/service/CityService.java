package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.repository.CityRepository;
import com.unibuc.forumApi.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService {
    private CityRepository  cityRepository;
    private CountryRepository countryRepository;

    public CityService(
            CityRepository cityRepository,
            CountryRepository countryRepository
    ) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public Optional<City> getCity(int id) {
        return cityRepository.getCity(id);
    }

    public Optional<List<City>> getCities() {
        return cityRepository.getCities();
    }

    public City create(City city) {
        return cityRepository.update(city);
    }

    public City update(City city) {
        return cityRepository.update(city);
    }

    public void removeCity(int id) {
        cityRepository.delete(id);
    }
}

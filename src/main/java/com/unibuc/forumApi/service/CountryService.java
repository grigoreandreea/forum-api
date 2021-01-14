package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.model.Country;
import com.unibuc.forumApi.repository.CityRepository;
import com.unibuc.forumApi.repository.CountryRepository;
import com.unibuc.forumApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {
    private CountryRepository countryRepository;
    private UserRepository userRepository;
    private CityRepository cityRepository;

    public CountryService(
            CountryRepository countryRepository,
            UserRepository userRepository,
            CityRepository cityRepository
    ) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    public Optional<Country> getCountry(int id) {
        return countryRepository.getCountry(id);
    }

    public Optional<List<Country>> getCountries() {
        return countryRepository.getCountries();
    }

    public Country create(Country country) {
        return countryRepository.update(country);
    }

    public Country update(Country country) {
        return countryRepository.update(country);
    }

    public void removeCountry(int id) {
        countryRepository.delete(id);
    }

    public Optional<List<City>> getCountryCities(int countryId) {
        return cityRepository.getCountryCities(countryId);
    }
}

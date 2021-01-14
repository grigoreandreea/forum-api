package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.Country;
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

    public CountryService(
            CountryRepository countryRepository,
            UserRepository userRepository
    ) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
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

}

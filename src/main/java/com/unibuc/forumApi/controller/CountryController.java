package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.config.Pagination;
import com.unibuc.forumApi.dto.CityRequest;
import com.unibuc.forumApi.dto.CountryRequest;
import com.unibuc.forumApi.dto.CountryWithCities;
import com.unibuc.forumApi.exception.CityNotFoundException;
import com.unibuc.forumApi.exception.CountryNotFoundException;
import com.unibuc.forumApi.mapper.CityMapper;
import com.unibuc.forumApi.mapper.CountryMapper;
import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.model.Country;
import com.unibuc.forumApi.service.CityService;
import com.unibuc.forumApi.service.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    private final CityService cityService;
    private final CityMapper cityMapper;

    public CountryController(
            CountryService countryService,
            CountryMapper countryMapper,
            CityService cityService,
            CityMapper cityMapper
    ) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @GetMapping
    @ResponseBody
    public List<Country> getCountries(Integer page, Integer size, String sort) {
        return new Pagination<>(countryService.getCountries(), page, size, sort)
                .paginate(Comparator.comparing(Country::getName));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Country> getCountry(@PathVariable int id) {
        return countryService.getCountry(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a Country",
            notes = "Creates a new Country based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Country was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Country> createCountry(@RequestBody CountryRequest countryRequest) {
        Country mappedCountry = countryMapper.countryRequestToCountry(countryRequest);
        Country savedCountry = countryService.create(mappedCountry);
        return ResponseEntity.created(URI.create("/countries/" + savedCountry.getId()))
                .body(savedCountry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody CountryRequest countryRequest) {
        Country mappedCountry = countryMapper.countryRequestToCountry(countryRequest);
        mappedCountry.setId(id);
        Country savedCountry = countryService.create(mappedCountry);
        return ResponseEntity.created(URI.create("/countries/" + savedCountry.getId()))
                .body(savedCountry);
    }

    @DeleteMapping("/{id}")
    public void removeCountry(@PathVariable int id) {
        countryService.removeCountry(id);
    }

    @GetMapping("/{id}/cities")
    @ResponseBody
    public CountryWithCities getCountryCities(@PathVariable int id) {
        Optional<Country> country = countryService.getCountry(id);
        if(country.isEmpty()) {
            throw new CountryNotFoundException(id);
        }

        Optional<List<City>> cities = countryService.getCountryCities(id);
        if(cities.isEmpty()) {
            return new CountryWithCities(country.get());
        }

        return new CountryWithCities(country.get(), cities.get());
    }

    @GetMapping("/{id}/cities/{idCity}")
    @ResponseBody
    public Optional<City> getCountryCities(@PathVariable int id, @PathVariable int idCity) {
        Optional<Country> country = countryService.getCountry(id);
        if(country.isEmpty()) {
            throw new CountryNotFoundException(id);
        }

        Optional<List<City>> cities = countryService.getCountryCities(id);
        if(cities.isEmpty()) {
            throw new CityNotFoundException(idCity);
        }

        return cities.get().stream().filter(city -> city.getId() == idCity).findFirst();
    }

    @PostMapping("/{id}/cities")
    public ResponseEntity<City> createCity(@PathVariable int id, @RequestBody CityRequest cityRequest) {
        Optional<Country> country = countryService.getCountry(id);
        if(country.isEmpty()) {
            throw new CountryNotFoundException(id);
        }

        cityRequest.setCountryId(id);
        City mappedCity = cityMapper.cityRequestToCity(cityRequest);
        City savedCity = cityService.create(mappedCity);
        return ResponseEntity.created(URI.create("/comments/" + savedCity.getId()))
                .body(savedCity);
    }

    @PutMapping("/{id}/cities/{idCity}")
    public ResponseEntity<City> updateCity(@PathVariable int id, @PathVariable int idCity, @RequestBody CityRequest cityRequest) {
        Optional<Country> country = countryService.getCountry(id);
        if(country.isEmpty()) {
            throw new CountryNotFoundException(id);
        }

        cityRequest.setCountryId(id);
        City mappedCity = cityMapper.cityRequestToCity(cityRequest);
        mappedCity.setId(idCity);
        City savedCity = cityService.create(mappedCity);
        return ResponseEntity.created(URI.create("/comments/" + savedCity.getId()))
                .body(savedCity);
    }

    @DeleteMapping("/{id}/cities/{idCity}")
    public void removeComment(@PathVariable int id, @PathVariable int idCity) {
        cityService.removeCity(idCity);
    }
}

package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.dto.CityRequest;
import com.unibuc.forumApi.mapper.CityMapper;
import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    public CityController(
            CityService cityService,
            CityMapper cityMapper
    ) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @GetMapping
    @ResponseBody
    public Optional<List<City>> getCities() { return cityService.getCities(); }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<City> getCity(@PathVariable int id) { return cityService.getCity(id); }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody CityRequest cityRequest) {
        City mappedCity = cityMapper.cityRequestToCity(cityRequest);
        City savedCity = cityService.create(mappedCity);
        return ResponseEntity.created(URI.create("/cities/" + savedCity.getId())).body(savedCity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable int id, @RequestBody CityRequest cityRequest) {
        City mappedCity = cityMapper.cityRequestToCity(cityRequest);
        mappedCity.setId(id);
        City savedCity = cityService.update(mappedCity);
        return ResponseEntity.created(URI.create("/cities/" + savedCity.getId())).body(savedCity);
    }

    @DeleteMapping("/{id}")
    public void removeCity(@PathVariable int id) {
        cityService.removeCity(id);
    }
}

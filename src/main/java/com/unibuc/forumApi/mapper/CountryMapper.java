package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.CountryRequest;
import com.unibuc.forumApi.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public Country countryRequestToCountry(CountryRequest countryRequest) {
        return new Country(
                countryRequest.getName()
        );
    }
}

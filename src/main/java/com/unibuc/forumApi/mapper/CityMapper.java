package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.CityRequest;
import com.unibuc.forumApi.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public City cityRequestToCity(CityRequest cityRequest)  {
        return new City(
                cityRequest.getName(),
                cityRequest.getCountryId()
        );
    }
}

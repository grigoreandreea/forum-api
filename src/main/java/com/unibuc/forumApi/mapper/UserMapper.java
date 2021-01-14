package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.UserRequest;
import com.unibuc.forumApi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userRequestToUser(UserRequest userRequest) {
        return new User(
                userRequest.getUsername(),
                userRequest.getDate(),
                userRequest.isGender(),
                userRequest.getCountryId(),
                userRequest.getCityId()
        );
    }
}

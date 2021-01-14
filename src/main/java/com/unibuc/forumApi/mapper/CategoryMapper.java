package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.CategoryRequest;
import com.unibuc.forumApi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        return new Category(
                categoryRequest.getName()
        );
    }
}

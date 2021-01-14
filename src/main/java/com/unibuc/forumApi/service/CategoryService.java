package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.model.Category;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.repository.CategoryRepository;
import com.unibuc.forumApi.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;
    
    public CategoryService(CategoryRepository categoryRepository, TopicRepository topicRepository) {
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Optional<List<Category>> getCategories() {
        return categoryRepository.getCategories();
    }

    public Category create(Category category) {
        return categoryRepository.update(category);
    }

    public Category update(Category category) {
        return categoryRepository.update(category);
    }

    public void removeCategory(int id) {
        categoryRepository.delete(id);
    }

    public Optional<List<Topic>> getCategoryTopics(int categoryId) {
        return topicRepository.getCategoryTopics(categoryId);
    }

}

package com.unibuc.forumApi.dto;

import com.unibuc.forumApi.model.Category;
import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.model.Country;
import com.unibuc.forumApi.model.Topic;

import java.util.List;

public class CategoryWithTopics {
    private Category category;
    private List<Topic> topics;

    public CategoryWithTopics(Category category, List<Topic> topics) {
        this.category = category;
        this.topics = topics;
    }

    public CategoryWithTopics(Category category) {
        this.category = category;
    }

    public CategoryWithTopics() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}

package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.TopicRequest;
import com.unibuc.forumApi.model.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public Topic topicRequestToTopic(TopicRequest topicRequest) {
        return new Topic(
          topicRequest.getName(),
          topicRequest.getDescription(),
          topicRequest.getUserId(),
          topicRequest.getCategoryId()
        );
    }
}

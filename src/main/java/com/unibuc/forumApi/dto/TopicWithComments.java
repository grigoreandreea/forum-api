package com.unibuc.forumApi.dto;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;

import java.util.List;

public class TopicWithComments {
    private Topic topic;
    private List<Comment> comments;

    public TopicWithComments() {
    }

    public TopicWithComments(Topic topic) {
        this.topic = topic;
    }

    public TopicWithComments(Topic topic, List<Comment> comments) {
        this.topic = topic;
        this.comments = comments;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

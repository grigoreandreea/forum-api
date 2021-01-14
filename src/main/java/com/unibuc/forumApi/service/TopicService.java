package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.repository.CommentRepository;
import com.unibuc.forumApi.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicService {
    private TopicRepository topicRepository;
    private CommentRepository commentRepository;

    public TopicService(
            TopicRepository topicRepository,
            CommentRepository commentRepository
    ) {
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
    }

    public Optional<Topic> getTopic(int topicId) {
        return topicRepository.getTopic(topicId);
    }

    public Optional<List<Topic>> getTopics() {
        return topicRepository.getTopics();
    }

    public Optional<List<Comment>> getTopicComments(int topicId) {
        return commentRepository.getTopicComments(topicId);
    }

    public Topic create(Topic topic) {
        return topicRepository.update(topic);
    }

    public Topic update(Topic topic) {
        return topicRepository.update(topic);
    }

    public void removeTopic(int id) {
        topicRepository.delete(id);
    }
}

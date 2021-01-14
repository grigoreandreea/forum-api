package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.repository.CommentRepository;
import com.unibuc.forumApi.repository.TopicRepository;
import com.unibuc.forumApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private TopicRepository topicRepository;
    private CommentRepository commentRepository;

    public UserService(
            UserRepository userRepository,
            TopicRepository topicRepository,
            CommentRepository commentRepository
    ) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
    }

    public Optional<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    public User create(User user) {
        return userRepository.update(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void removeUser(int id) {
        userRepository.delete(id);
    }

    public Optional<List<Topic>> getUserTopics(int userId) {
        return topicRepository.getUserTopics(userId);
    }

    public Optional<List<Comment>> getUserComments(int id) {
        return commentRepository.getUserComments(id);
    }
}

package com.unibuc.forumApi.service;

import com.unibuc.forumApi.dto.CompanyRequest;
import com.unibuc.forumApi.exception.UserNotFoundException;
import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Company;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.repository.CommentRepository;
import com.unibuc.forumApi.repository.CompanyRepository;
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
    private CompanyRepository companyRepository;

    public UserService(
            UserRepository userRepository,
            TopicRepository topicRepository,
            CommentRepository commentRepository,
            CompanyRepository companyRepository
    ) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
        this.companyRepository = companyRepository;
    }

    public Optional<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    public Optional<User> getUser(int id) {
        Optional<User> user = userRepository.getUser(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        Optional<List<Company>> employers = companyRepository.getCompaniesByUser(id);
        if (employers.isPresent()) {
            user.get().setEmployers(employers.get());
        }
        return user;
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

    public void saveUserEmployers(int id, List<Integer> employers) {
        userRepository.deleteExistingUserEmployers(id);
        if (employers.size() > 0) {
            userRepository.saveUserEmployers(id, employers);
        }
    }
}

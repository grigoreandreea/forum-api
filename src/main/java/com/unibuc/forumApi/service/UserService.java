package com.unibuc.forumApi.service;

import com.unibuc.forumApi.exception.UserNotFoundException;
import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Company;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.repository.CommentRepository;
import com.unibuc.forumApi.repository.CompanyRepository;
import com.unibuc.forumApi.repository.TopicRepository;
import com.unibuc.forumApi.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            TopicRepository topicRepository,
            CommentRepository commentRepository,
            CompanyRepository companyRepository,
            @Lazy PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
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

    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByName(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(1);
        }
        Optional<List<Company>> employers = companyRepository.getCompaniesByUser(user.get().getId());
        if (employers.isPresent()) {
            user.get().setEmployers(employers.get());
        }
        return user;
    }

    public User create(User user) {
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.update(user);
    }

    public User update(User user) {
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
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

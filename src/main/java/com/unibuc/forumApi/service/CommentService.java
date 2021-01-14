package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> getComment(int id) {
        return commentRepository.getComment(id);
    }

    public Optional<List<Comment>> getComments() {
        return commentRepository.getComments();
    }

    public Comment create(Comment comment) {
        return commentRepository.update(comment);
    }

    public Comment update(Comment comment) {
        return commentRepository.update(comment);
    }

    public void removeComment(int id) {
        commentRepository.delete(id);
    }
}

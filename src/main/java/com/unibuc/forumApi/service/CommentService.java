package com.unibuc.forumApi.service;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

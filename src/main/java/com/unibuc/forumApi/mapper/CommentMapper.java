package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.CommentRequest;
import com.unibuc.forumApi.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment commentRequestToComment(CommentRequest commentRequest) {
        return new Comment(
                commentRequest.getDescription(),
                commentRequest.getTopicId(),
                commentRequest.getUserId()
        );
    }
}


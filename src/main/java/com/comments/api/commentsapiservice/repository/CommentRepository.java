package com.comments.api.commentsapiservice.repository;

import com.comments.api.commentsapiservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthor(String username);
    List<Comment> findByDateOfCommentBetween(LocalDateTime start, LocalDateTime end);
}
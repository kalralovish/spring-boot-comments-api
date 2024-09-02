package com.comments.api.commentsapiservice.repository;

import com.comments.api.commentsapiservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthor(String username);
    List<Comment> findByDateOfCommentBetween(LocalDateTime start, LocalDateTime end);
    List<Comment> findByAuthorAndDateOfCommentBetween(String author, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT * FROM comments WHERE author = :author AND dateofcomment BETWEEN :start AND :end", nativeQuery = true)
    List<Comment> findByAuthorAndDateOfCommentBetweenNative(
        @Param("author") String author,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );
}
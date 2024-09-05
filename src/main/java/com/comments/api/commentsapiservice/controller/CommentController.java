package com.comments.api.commentsapiservice.controller;

import com.comments.api.commentsapiservice.model.Comment;
import com.comments.api.commentsapiservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comments.table.name}")
    private String tableName;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getComments(@RequestParam Map<String, String> filters) {
        try {
            List<Comment> comments = commentService.getComments(filters);
            List<Map<String, Object>> transformedComments = transformComments(comments);
            return createResponse(HttpStatus.OK, "Records retrieved successfully", transformedComments, null);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, null, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(comment);
            return createResponse(HttpStatus.CREATED, "Comment created successfully", createdComment, null);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, null, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateComment(@PathVariable Long id,
            @RequestBody Comment commentDetails) {
        try {
            Comment updatedComment = commentService.updateComment(id, commentDetails);
            return createResponse(HttpStatus.OK, "Comment updated successfully", updatedComment, null);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, null, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return createResponse(HttpStatus.OK, "Comment deleted successfully", null, null);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, null, e.getMessage());
        }
    }

    private ResponseEntity<Map<String, Object>> createResponse(HttpStatus status, String message, Object data,
            String error) {
        Map<String, Object> body = new HashMap<>();
        body.put("statusCode", status.value());
        body.put("message", message != null ? message : "");
        body.put("data", data != null ? data : "");
        body.put("error", error != null ? error : "");
        return new ResponseEntity<>(body, status);
    }

    private List<Map<String, Object>> transformComments(List<Comment> comments) {
        return comments.stream().map(comment -> {
            Map<String, Object> transformedComment = new HashMap<>();
            transformedComment.put("id", comment.getId());
            if ("comments_table1".equals(tableName)) {
                transformedComment.put("createdBy", comment.getCreatedBy());
                transformedComment.put("createDate", comment.getCreateDate());
                transformedComment.put("updatedBy", comment.getUpdatedBy());
                transformedComment.put("updateDate", comment.getUpdateDate());
                transformedComment.put("mobile", comment.getMobile());
            } else {
                transformedComment.put("age", comment.getAge());
                transformedComment.put("email", comment.getEmail());
                transformedComment.put("text", comment.getText());
                transformedComment.put("ipAddress", comment.getIpAddress());
                transformedComment.put("status", comment.getStatus());
            }
            return transformedComment;
        }).collect(java.util.stream.Collectors.toList());
    }
}
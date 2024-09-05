package com.comments.api.commentsapiservice.service;

import com.comments.api.commentsapiservice.model.Comment;
import com.comments.api.commentsapiservice.repository.CommentRepository;
import com.comments.api.commentsapiservice.repository.CommentSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Value("${comments.filter.use-or-logic}")
    private boolean useOrLogic;

    @Value("${comments.filter.apply-current-datetime}")
    private boolean applyCurrentDateTime;

    @Value("${comments.query.include-soft-deleted}")
    private boolean includeSoftDeleted;

    @Value("${comments.result.limit}")
    private int resultLimit;

    @Transactional
    public List<Comment> getComments(Map<String, String> filters) {
        Map<String, String> modifiedFilters = new HashMap<>(filters);
        if (applyCurrentDateTime && !modifiedFilters.containsKey("createDate")) {
            modifiedFilters.put("createDate",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        Specification<Comment> spec = CommentSpecification.withFilters(modifiedFilters, useOrLogic, includeSoftDeleted);
        Page<Comment> page = commentRepository.findAll(spec, PageRequest.of(0, resultLimit));
        return page.getContent();
    }

    @Transactional
    public Comment createComment(Comment comment) {
        comment.setCreateDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        comment.setUpdatedBy(commentDetails.getUpdatedBy());
        comment.setUpdateDate(LocalDateTime.now());
        comment.setMobile(commentDetails.getMobile());
        comment.setAge(commentDetails.getAge());
        comment.setEmail(commentDetails.getEmail());
        comment.setText(commentDetails.getText());
        comment.setIpAddress(commentDetails.getIpAddress());
        comment.setStatus(commentDetails.getStatus());

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        comment.softDelete();
        commentRepository.save(comment);
    }
}
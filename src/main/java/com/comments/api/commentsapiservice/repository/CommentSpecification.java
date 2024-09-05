package com.comments.api.commentsapiservice.repository;

import com.comments.api.commentsapiservice.model.Comment;
import com.comments.api.commentsapiservice.model.CommentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentSpecification {

    private static final Logger log = LoggerFactory.getLogger(CommentSpecification.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Specification<Comment> withFilters(Map<String, String> filters, boolean useOrLogic,
            boolean includeSoftDeleted) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    switch (key) {
                        case "id":
                            predicates.add(criteriaBuilder.equal(root.get(key), Long.parseLong(value)));
                            break;
                        case "createdBy":
                        case "updatedBy":
                        case "mobile":
                        case "email":
                        case "text":
                        case "ipAddress":
                            predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                            break;
                        case "age":
                            predicates.add(criteriaBuilder.equal(root.get(key), Integer.parseInt(value)));
                            break;
                        case "status":
                            predicates.add(criteriaBuilder.equal(root.get(key), CommentStatus.valueOf(value)));
                            break;
                        case "createDate":
                        case "updateDate":
                            LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
                            predicates.add(criteriaBuilder.equal(root.get(key), dateTime));
                            break;
                    }
                }
            });

            if (!includeSoftDeleted) {
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            }

            Predicate finalPredicate;
            if (useOrLogic && predicates.size() > 1) {
                finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            } else {
                finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }

            query.where(finalPredicate);

            return finalPredicate;
        };
    }
}
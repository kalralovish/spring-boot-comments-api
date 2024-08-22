package com.comments.api.commentsapiservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "dateofcomment", nullable = false)
    private LocalDateTime dateOfComment;
}
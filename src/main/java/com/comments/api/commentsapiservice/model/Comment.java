package com.comments.api.commentsapiservice.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdby", nullable = false)
    private String createdBy;

    @Column(name = "createdate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "updatedby")
    private String updatedBy;

    @Column(name = "updatedate")
    private LocalDateTime updateDate;

    private String mobile;

    private Integer age;

    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "ipaddress")
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    @Column(name = "isdeleted", nullable = false)
    private boolean isDeleted = false;

    public void softDelete() {
        this.isDeleted = true;
        this.updateDate = LocalDateTime.now();
    }
}
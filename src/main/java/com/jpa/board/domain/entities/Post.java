package com.jpa.board.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
@SequenceGenerator(
        name = "post_seq_generator",
        sequenceName = "post_seq",
        initialValue = 1, allocationSize = 50)
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "post_seq_generator")
    @Column(name = "post_id", updatable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Post()  { }

    public Post(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post(String title, String content, User user) {
        this(null, title, content, user);
    }

    public Post(String title, String content) {
        this(title, content, null);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public void updatePost(String title, String content){
        this.title = title;
        this.content = content;
    }
}

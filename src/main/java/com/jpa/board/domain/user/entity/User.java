package com.jpa.board.domain.user.entity;

import com.jpa.board.domain.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@SequenceGenerator(
        name = "user_seq_generator",
        sequenceName = "user_seq",
        initialValue = 1, allocationSize = 50)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_seq_generator")
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    private String hobby;

    @Column(nullable = true, length = 3)
    private int age;


}

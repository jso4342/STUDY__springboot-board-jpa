package com.jpa.board.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@SequenceGenerator(
        name = "user_seq_generator",
        sequenceName = "user_seq",
        initialValue = 1, allocationSize = 50)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_seq_generator")
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 20)
    @ColumnDefault("/")
    private String hobby;

    @Column(nullable = false, length = 3)
    private int age;

    protected User(){ }

    public User(Long id, String name, String hobby, int age) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.age = age;
    }

    public User(String name, String hobby, int age) {
        this(null, name, hobby, age);
    }

    public User(String name, int age) {
        this(name, null, age);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHobby() {
        return hobby;
    }

    public int getAge() {
        return age;
    }
}

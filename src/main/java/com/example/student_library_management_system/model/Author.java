package com.example.student_library_management_system.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="author")
public class Author {

    //id, name, email, gender, country, rating

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="rating", nullable = false)
    private double rating;

    @OneToMany(mappedBy = "author")
    private List<Book> booksWrittenByAuthor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
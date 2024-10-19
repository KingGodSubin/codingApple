package com.example.shop;

import jakarta.persistence.*;

@Entity
public class notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 2000)
    public String title;
    public String date;
}

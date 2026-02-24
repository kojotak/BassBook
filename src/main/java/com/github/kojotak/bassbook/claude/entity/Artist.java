package com.github.kojotak.bassbook.claude.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
public class Artist extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

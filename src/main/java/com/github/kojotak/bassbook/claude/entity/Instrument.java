package com.github.kojotak.bassbook.claude.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instrument")
public class Instrument extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Instrument() {
    }

    public Instrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

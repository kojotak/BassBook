package com.github.kojotak.bassbook.claude.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "y_channel")
public class YChannel extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String youtubeId;

    public YChannel() {
    }

    public YChannel(String name, String youtubeId) {
        this.name = name;
        this.youtubeId = youtubeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }
}

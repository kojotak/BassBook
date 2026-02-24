package com.github.kojotak.bassbook.claude.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "y_video")
public class YVideo extends BaseEntity {

    @Column(nullable = false)
    private String youtubeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    private YChannel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuning_id")
    private Tuning tuning;

    public YVideo() {
    }

    public YVideo(String youtubeId, YChannel channel, Song song, Instrument instrument) {
        this.youtubeId = youtubeId;
        this.channel = channel;
        this.song = song;
        this.instrument = instrument;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public YChannel getChannel() {
        return channel;
    }

    public void setChannel(YChannel channel) {
        this.channel = channel;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }
}

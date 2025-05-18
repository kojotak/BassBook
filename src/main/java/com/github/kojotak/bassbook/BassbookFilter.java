package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.lang.Nullable;

import java.util.function.Predicate;

public class BassbookFilter implements Predicate<Song> {

    @Nullable
    String songName;

    @Nullable
    Author author;

    @Nullable
    Tuning tuning;

    @Nullable
    Channel channel;

    @Nullable
    Technique technique;

    @Nullable
    Meter meter;

    @Nullable
    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    @Nullable
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Technique getTechnique() {
        return technique;
    }

    public void setTechnique(Technique technique) {
        this.technique = technique;
    }

    @Override
    public String toString() {
        return "BassbookFilter{" +
                "songName=" + songName +
                ", author=" + author +
                ", meter=" + meter +
                ", tuning=" + tuning +
                ", channel=" + channel +
                ", technique=" + technique +
                '}';
    }

    @Override
    public boolean test(Song song) {
        var result = true;
        result &= songName == null || song.getName().toLowerCase().contains(songName.toLowerCase().trim());
        result &= author == null || author.equals(song.author());
        result &= tuning == null || song.getAllTunings().contains(tuning);
        result &= channel == null || song.getAllChannels().contains(channel);
        result &= technique == null || song.getAllTechnique().contains(technique);
        result &= meter == null || song.meter().equals(meter);
        return result;
    }
}

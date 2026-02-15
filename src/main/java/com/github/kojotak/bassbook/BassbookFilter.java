package com.github.kojotak.bassbook;

import com.github.kojotak.bassbook.data.*;
import org.springframework.lang.Nullable;

import java.util.function.Predicate;

public class BassbookFilter implements Predicate<Row> {

    @Nullable
    String songName;

    @Nullable
    String authorName;

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


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String author) {
        this.authorName = author;
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
                ", authorName=" + authorName +
                ", meter=" + meter +
                ", tuning=" + tuning +
                ", channel=" + channel +
                ", technique=" + technique +
                '}';
    }

    @Override
    public boolean test(Row row) {
        var result = true;
        result &= songName == null || row.song().getName().toLowerCase().contains(songName.toLowerCase().trim());
        result &= authorName == null || authorName.equals(row.author().name());
        result &= tuning == null || row.song().getAllTunings().contains(tuning);
        result &= channel == null || row.song().getAllChannels().contains(channel);
        result &= technique == null || row.song().getAllTechnique().contains(technique);
        result &= meter == null || row.song().meterOrDefault().equals(meter);
        return result;
    }
}

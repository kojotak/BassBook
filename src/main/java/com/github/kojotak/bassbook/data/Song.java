package com.github.kojotak.bassbook.data;

import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.IntStream;

public record Song (

        String name,
        Author author,
        Meter meter,
        Feel feel,
        Collection<Youtube> plays

) implements Named {

    @Override
    public String getName(){
        return name();
    }

    public List<Tuning> getAllTunings(){
        return plays.stream().map(Youtube::tuning).distinct().sorted().toList();
    }

    public List<String> getAllTuningNames(){
        return getAllTunings().stream().map(Tuning::getName).toList();
    }

    public List<Technique> getAllTechnique(){
        return plays.stream().map(Youtube::technique).flatMap(EnumSet::stream).distinct().sorted().toList();
    }

    public List<Channel> getAllChannels(){
        return plays.stream().map(Youtube::channel).distinct().sorted().toList();
    }

    public static SongBuilder from(Author author){
        return new SongBuilder().author(author);
    }

    public static class SongBuilder {

        private Author author;
        private String name;
        private Meter meter = Meter.COMMON;
        private Feel feel = Feel.STRAIGHT;
        private final List<Youtube> plays = new ArrayList<>();

        public SongBuilder name(String name){
            this.name = name;
            return this;
        }

        public SongBuilder author(Author author){
            this.author = author;
            return this;
        }

        public SongBuilder meter(Meter meter){
            this.meter = meter;
            return this;
        }

        public SongBuilder meter(int count, int perBeat){
            return meter(new Meter(count, perBeat));
        }

        public SongBuilder feel(Feel feel){
            this.feel = feel;
            return this;
        }

        public SongBuilder youtube(Channel channel, String id){
            plays.add(new Youtube(channel, id));
            return this;
        }

        public SongBuilder youtube(Channel channel, String id, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, technique));
            return this;
        }

        public SongBuilder youtube(Channel channel, String id, Tuning tuning){
            plays.add(new Youtube(channel, id, tuning, EnumSet.noneOf(Technique.class)));
            return this;
        }

        public SongBuilder youtube(Channel channel, String id, Tuning tuning, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, tuning, technique));
            return this;
        }

        public Song build(){
            return buildValid(name, author, meter, feel, plays);
        }

        public SongsBuilder next(){
            return new SongsBuilder()
                    .author(author)
                    .name(name)
                    .feel(feel)
                    .meter(meter)
                    .youtube(plays)
                    .next();
        }
    }

    public static class SongsBuilder {

        private Author author;
        private int index = 0;
        private final List<String> names = new ArrayList<>();
        private final List<Meter> meters = new ArrayList<>();
        private final List<Feel> feels = new ArrayList<>();
        private final List<List<Youtube>> playList = new ArrayList<>();

        public SongsBuilder name(String name){
            names.add(index, name);
            return this;
        }

        public SongsBuilder author(Author author){
            Assert.isNull(this.author, "author can not be reset");
            this.author = author;
            return this;
        }

        public SongsBuilder meter(Meter meter){
            meters.add(index, meter);
            return this;
        }

        public SongsBuilder meter(int count, int perBeat){
            return meter(new Meter(count, perBeat));
        }

        public SongsBuilder feel(Feel feel){
            feels.add(index, feel);
            return this;
        }

        public SongsBuilder youtube(List<Youtube> plays){
            playList.add(index, plays);
            return this;
        }

        public SongsBuilder youtube(Channel channel, String id){
            playList.get(index).add(new Youtube(channel, id));
            return this;
        }

        public SongsBuilder youtube(Channel channel, String id, EnumSet<Technique> technique){
            playList.get(index).add(new Youtube(channel, id, technique));
            return this;
        }

        public SongsBuilder youtube(Channel channel, String id, Tuning tuning){
            playList.get(index).add(new Youtube(channel, id, tuning, EnumSet.noneOf(Technique.class)));
            return this;
        }

        public SongsBuilder youtube(Channel channel, String id, Tuning tuning, EnumSet<Technique> technique){
            playList.get(index).add(new Youtube(channel, id, tuning, technique));
            return this;
        }

        public List<Song> buildAll() {
            return IntStream.range(0, index + 1).mapToObj(
                    i -> buildValid(
                            names.get(i),
                            author,
                            meters.get(i),
                            feels.get(i),
                            playList.get(i)
                    )
            ).toList();
        }

        public SongsBuilder next(){
            index++;
            return withDefaults();
        }

        private SongsBuilder withDefaults(){
            this.playList.add(index, new ArrayList<>());
            return meter(Meter.COMMON).feel(Feel.STRAIGHT);
        }
    }

    private static Song buildValid(String name, Author author, Meter meter, Feel feel, Collection<Youtube> plays) {
        Assert.notEmpty(plays, "plays can not be empty");
        Objects.requireNonNull(name, "name is required");
        Objects.requireNonNull(author, "author is required");
        Objects.requireNonNull(feel, "feel can not be null");
        Objects.requireNonNull(meter, "meter can not be null");
        var sortedPlays = plays.stream()
                .sorted(Comparator.comparing(p -> p.channel().label))
                .toList();
        return new Song(name, author, meter, feel, sortedPlays);
    }

}

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

    public List<Technique> getAllTechnique(){
        return plays.stream().map(Youtube::technique).flatMap(EnumSet::stream).distinct().sorted().toList();
    }

    public static SongBuilder from(Author author){
        var builder = new SongBuilder();
        builder.author = author;

        //set defaults
        return builder.withDefaults();
    }

    public static class SongBuilder {

        private Author author;
        private int index = 0;
        private List<String> names = new ArrayList<>();
        private List<Meter> meters = new ArrayList<>();
        private List<Feel> feels = new ArrayList<>();
        private final List<List<Youtube>> playList = new ArrayList<>();

        public SongBuilder name(String name){
            names.add(index, name);
            return this;
        }

        public SongBuilder author(Author author){
            Assert.isNull(author, "author can not be reset");
            this.author = author;
            return this;
        }

        public SongBuilder meter(Meter meter){
            meters.add(index, meter);
            return this;
        }

        public SongBuilder meter(int count, int perBeat){
            return meter(new Meter(count, perBeat));
        }

        public SongBuilder feel(Feel feel){
            feels.add(index, feel);
            return this;
        }

        public SongBuilder youtube(Channel channel, String id){
            playList.get(index).add(new Youtube(channel, id));
            return this;
        }

        public SongBuilder youtube(Channel channel, String id, EnumSet<Technique> technique){
            playList.get(index).add(new Youtube(channel, id, technique));
            return this;
        }

        public SongBuilder youtube(Channel channel, String id, Tuning tuning, EnumSet<Technique> technique){
            playList.get(index).add(new Youtube(channel, id, tuning, technique));
            return this;
        }

        public Song build(){
            Assert.isTrue(index == 0, "can not build one song from multiple songs");
            var name = names.getFirst();
            var feel = feels.getFirst();
            var meter = meters.getFirst();
            var plays = playList.getFirst();
            Assert.notEmpty(plays, "plays can not be empty");
            Objects.requireNonNull(name, "name is required");
            Objects.requireNonNull(author, "author is required");
            Objects.requireNonNull(feel, "feel can not be null");
            Objects.requireNonNull(meter, "meter can not be null");
            return new Song(
                    name,
                    author,
                    meter,
                    feel,
                    sorted(plays)
            );
        }

        public List<Song> buildAll(){
            return IntStream.range(0, index+1).mapToObj(
                    i -> new Song(
                            names.get(i),
                            author,
                            meters.get(i),
                            feels.get(i),
                            sorted(playList.get(i))
                    )
            ).toList();
        }

        public SongBuilder next(){
            index++;
            return withDefaults();
        }

        private List<Youtube> sorted(List<Youtube> plays){
            return plays.stream().sorted(Comparator.comparing(p -> p.channel().label)).toList();
        }

        private SongBuilder withDefaults(){
            this.playList.add(index, new ArrayList<>());
            return meter(Meter.COMMON).feel(Feel.STRAIGHT);
        }
    }

}

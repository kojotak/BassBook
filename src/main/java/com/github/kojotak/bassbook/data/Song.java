package com.github.kojotak.bassbook.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;
import org.springframework.lang.Nullable;
import java.util.*;
import java.util.stream.IntStream;

public record Song (

        String name,
        Meter meter,
        Feel feel,
        @Nullable Integer bpm,
        int aFrequency,
        Collection<Youtube> plays

) implements Named {

    //constructor with mandatory fields only
    public Song(String name,
                Meter meter,
                Feel feel,
                Collection<Youtube> plays){
        this(name, meter, feel, DEFAULT_BPM, DEFAULT_TUNING_FREQUENCY, plays);
    }

    public static final int DEFAULT_TUNING_FREQUENCY = 440;
    public static final Integer DEFAULT_BPM = null;

    @Override
    public String getName(){
        return name();
    }

    @JsonIgnore
    public List<Tuning> getAllTunings(){
        return plays.stream().map(Youtube::tuning).distinct().sorted().toList();
    }

    @JsonIgnore
    public List<String> getAllTuningNames(){
        return getAllTunings().stream().map(Tuning::getName).toList();
    }

    @JsonIgnore
    public List<Technique> getAllTechnique(){
        return plays.stream().map(Youtube::technique).flatMap(EnumSet::stream).distinct().sorted().toList();
    }

    @JsonIgnore
    public List<Channel> getAllChannels(){
        return plays.stream().map(Youtube::channel).distinct().sorted().toList();
    }

    public static SongBuilder name(String name){
        return new SongBuilder().name(name);
    }

    public static class SongBuilder {

        private String name;
        private Meter meter = Meter.COMMON;
        private Feel feel = Feel.STRAIGHT;
        private @Nullable Integer bpm = DEFAULT_BPM;
        private int aFrequency = DEFAULT_TUNING_FREQUENCY;
        private final List<Youtube> plays = new ArrayList<>();

        public SongBuilder name(String name){
            this.name = name;
            return this;
        }

        public SongBuilder meter(Meter meter){
            this.meter = meter;
            return this;
        }

        public SongBuilder meter(int count, int perBeat){
            return meter(new Meter(count, perBeat));
        }

        public SongBuilder frequency(int aFrequency){
            this.aFrequency = aFrequency;
            return this;
        }

        public SongBuilder bpm(Integer bpm){
            this.bpm = bpm;
            return this;
        }

        public SongBuilder feel(Feel feel){
            this.feel = feel;
            return this;
        }

        public SongBuilder youtubeAnd(Channel channel, String id){
            plays.add(new Youtube(channel, id));
            return this;
        }

        public Song youtube(Channel channel, String id){
            plays.add(new Youtube(channel, id));
            return build();
        }

        public SongBuilder youtubeAnd(Channel channel, String id, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, technique));
            return this;
        }

        public Song youtube(Channel channel, String id, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, technique));
            return build();
        }

        public SongBuilder youtubeAnd(Channel channel, String id, Tuning tuning){
            plays.add(new Youtube(channel, id, tuning, EnumSet.noneOf(Technique.class)));
            return this;
        }

        public Song youtube(Channel channel, String id, Tuning tuning){
            plays.add(new Youtube(channel, id, tuning, EnumSet.noneOf(Technique.class)));
            return build();
        }

        public SongBuilder youtubeAnd(Channel channel, String id, Tuning tuning, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, tuning, technique));
            return this;
        }

        public Song youtube(Channel channel, String id, Tuning tuning, EnumSet<Technique> technique){
            plays.add(new Youtube(channel, id, tuning, technique));
            return build();
        }

        public Song build(){
            return buildValid(name, meter, feel, bpm, aFrequency, plays);
        }

        public SongsBuilder next(){
            return new SongsBuilder()
                    .name(name)
                    .feel(feel)
                    .meter(meter)
                    .bpm(DEFAULT_BPM)
                    .frequency(DEFAULT_TUNING_FREQUENCY)
                    .youtube(plays)
                    .next();
        }
    }

    public static class SongsBuilder {

        private int index = 0;
        private final List<String> names = new ArrayList<>();
        private final List<Meter> meters = new ArrayList<>();
        private final List<Feel> feels = new ArrayList<>();
        private final List<Integer> bpms = new ArrayList<>();
        private final List<Integer> freqs = new ArrayList<>();
        private final List<List<Youtube>> playList = new ArrayList<>();

        public SongsBuilder name(String name){
            names.add(index, name);
            return this;
        }

        public SongsBuilder meter(Meter meter){
            meters.add(index, meter);
            return this;
        }

        public SongsBuilder meter(int count, int perBeat){
            return meter(new Meter(count, perBeat));
        }

        public SongsBuilder bpm(Integer bpm){
            bpms.add(index, bpm);
            return this;
        }

        public SongsBuilder frequency(int aFrequency){
            freqs.add(index, aFrequency);
            return this;
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
                            meters.get(i),
                            feels.get(i),
                            bpms.get(i),
                            freqs.get(i),
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
            return meter(Meter.COMMON).feel(Feel.STRAIGHT).bpm(DEFAULT_BPM).frequency(DEFAULT_TUNING_FREQUENCY);
        }
    }

    private static Song buildValid(String name, Meter meter, Feel feel, Integer bpm, int freq, Collection<Youtube> plays) {
        Assert.notEmpty(plays, "plays can not be empty");
        Objects.requireNonNull(name, "name is required");
        Objects.requireNonNull(feel, "feel can not be null");
        Objects.requireNonNull(meter, "meter can not be null");
        var sortedPlays = plays.stream()
                .sorted(Comparator.comparing(p -> p.channel().label))
                .toList();
        return new Song(name, meter, feel, bpm, freq, sortedPlays);
    }

}

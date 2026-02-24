package com.github.kojotak.bassbook.claude.controller;

import com.github.kojotak.bassbook.claude.entity.*;
import com.github.kojotak.bassbook.claude.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final YChannelRepository channelRepository;
    private final InstrumentRepository instrumentRepository;
    private final TuningRepository tuningRepository;
    private final YVideoRepository videoRepository;

    public ApiController(ArtistRepository artistRepository,
                         SongRepository songRepository,
                         YChannelRepository channelRepository,
                         InstrumentRepository instrumentRepository,
                         TuningRepository tuningRepository,
                         YVideoRepository videoRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.channelRepository = channelRepository;
        this.instrumentRepository = instrumentRepository;
        this.tuningRepository = tuningRepository;
        this.videoRepository = videoRepository;
    }

    @GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> createArtist(@RequestBody Map<String, String> request) {
        var name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var artist = new Artist(name.trim());
        return ResponseEntity.ok(artistRepository.save(artist));
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs(@RequestParam(required = false) Long artistId) {
        if (artistId != null) {
            return artistRepository.findById(artistId)
                    .map(artist -> songRepository.findAll().stream()
                            .filter(song -> song.getArtist() != null && 
                                          song.getArtist().getId().equals(artistId))
                            .toList())
                    .orElse(List.of());
        }
        return songRepository.findAll();
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> createSong(@RequestBody Map<String, Object> request) {
        var name = (String) request.get("name");
        var artistId = Long.valueOf(request.get("artistId").toString());

        if (name == null || name.trim().isEmpty() || artistId == null) {
            return ResponseEntity.badRequest().build();
        }

        return artistRepository.findById(artistId)
                .map(artist -> {
                    var song = new Song(name.trim(), artist);
                    return ResponseEntity.ok(songRepository.save(song));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/channels")
    public List<YChannel> getAllChannels() {
        return channelRepository.findAll();
    }

    @PostMapping("/channels")
    public ResponseEntity<YChannel> createChannel(@RequestBody Map<String, String> request) {
        var name = request.get("name");
        var youtubeId = request.get("youtubeId");

        if (name == null || name.trim().isEmpty() || 
            youtubeId == null || youtubeId.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var channel = new YChannel(name.trim(), youtubeId.trim());
        return ResponseEntity.ok(channelRepository.save(channel));
    }

    @GetMapping("/instruments")
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @PostMapping("/instruments")
    public ResponseEntity<Instrument> createInstrument(@RequestBody Map<String, String> request) {
        var name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var instrument = new Instrument(name.trim());
        return ResponseEntity.ok(instrumentRepository.save(instrument));
    }

    @GetMapping("/tunings")
    public List<Tuning> getTuningsByInstrument(@RequestParam Long instrumentId) {
        return tuningRepository.findByInstrumentIdOrderByIdAsc(instrumentId);
    }

    @PostMapping("/videos")
    public ResponseEntity<Map<String, Object>> createVideo(@RequestBody Map<String, Object> request) {
        try {
            var youtubeId = (String) request.get("youtubeId");
            var songId = Long.valueOf(request.get("songId").toString());
            var channelId = Long.valueOf(request.get("channelId").toString());
            var instrumentId = Long.valueOf(request.get("instrumentId").toString());
            var tuningId = request.get("tuningId") != null ? Long.valueOf(request.get("tuningId").toString()) : null;

            if (youtubeId == null || youtubeId.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "YouTube ID is required"));
            }

            var song = songRepository.findById(songId).orElse(null);
            var channel = channelRepository.findById(channelId).orElse(null);
            var instrument = instrumentRepository.findById(instrumentId).orElse(null);
            var tuning = tuningId != null ? tuningRepository.findById(tuningId).orElse(null) : null;

            if (song == null || channel == null || instrument == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid entity references"));
            }

            if (tuningId != null && tuning == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid tuning reference"));
            }

            var video = new YVideo(youtubeId.trim(), channel, song, instrument);
            video.setTuning(tuning);
            var savedVideo = videoRepository.save(video);

            var response = new HashMap<String, Object>();
            response.put("id", savedVideo.getId());
            response.put("youtubeId", savedVideo.getYoutubeId());
            response.put("message", "Video saved successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

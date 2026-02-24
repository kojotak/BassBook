package com.github.kojotak.bassbook.claude.service;

import com.github.kojotak.bassbook.claude.entity.*;
import com.github.kojotak.bassbook.claude.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class ExportService {

    private final InstrumentRepository instrumentRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final YChannelRepository yChannelRepository;
    private final YVideoRepository yVideoRepository;
    private final TuningRepository tuningRepository;

    public ExportService(InstrumentRepository instrumentRepository, ArtistRepository artistRepository, SongRepository songRepository, YChannelRepository yChannelRepository, YVideoRepository yVideoRepository, TuningRepository tuningRepository) {
        this.instrumentRepository = instrumentRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.yChannelRepository = yChannelRepository;
        this.yVideoRepository = yVideoRepository;
        this.tuningRepository = tuningRepository;
    }

    public String generateSqlExport() {
        var sql = new StringBuilder();

        // Export Instruments
        var instruments = exportAllEntities(sql, "INSERT INTO instrument\n(id, name) VALUES", instrumentRepository,
                i -> String.format("(%d, '%s')", i.getId(), escapeSql(i.getName())));

        // Export Instrument Tunings
        var tunings = exportAllEntities(sql, "INSERT INTO tuning\n(id, name) VALUES", tuningRepository,
                t -> String.format("(%d, '%s')", t.getId(), escapeSql(t.getName())));

        // Export Artists
        var artists = exportAllEntities(sql, "INSERT INTO artist\n(id, name) VALUES", artistRepository,
                a -> String.format("(%d, '%s')", a.getId(), escapeSql(a.getName())));

        // Export Songs
        var songs = exportAllEntities(sql, "INSERT INTO song\n(id, name, artist_id) VALUES", songRepository,
                s -> String.format("(%d, '%s', %d)", s.getId(), escapeSql(s.getName()), id(s.getArtist())));

        // Export YChannels
        var channels = exportAllEntities(sql, "INSERT INTO y_channel\n(id, name, youtube_id) VALUES", yChannelRepository,
                ch -> String.format("(%d, '%s', '%s')", ch.getId(), escapeSql(ch.getName()), escapeSql(ch.getYoutubeId())));

        // Export YVideos
        var videos = exportAllEntities(sql, "INSERT INTO y_video\n(id, youtube_id, channel_id, song_id, instrument_id, tuning_id) VALUES", yVideoRepository,
                v -> String.format("(%d, '%s', %d, %d, %d, %d)",
                        v.getId(), escapeSql(v.getYoutubeId()), id(v.getChannel()), id(v.getSong()), id(v.getInstrument()), id(v.getTuning())));

        // Reset sequences
        sql.append("-- Reset sequences to avoid primary key conflicts\n");
        Map<String, List<BaseEntity>> resetMap = Map.ofEntries(
                new AbstractMap.SimpleEntry("instrument", instruments),
                new AbstractMap.SimpleEntry("tuning", tunings),
                new AbstractMap.SimpleEntry("artist", artists),
                new AbstractMap.SimpleEntry("song", songs),
                new AbstractMap.SimpleEntry("y_channel", channels),
                new AbstractMap.SimpleEntry("y_video", videos)
        );
        for(Map.Entry<String, List<BaseEntity>> entry : resetMap.entrySet()){
            var list = entry.getValue();
            if (!list.isEmpty()) {
                var maxId = list.stream().mapToLong(BaseEntity::getId).max().orElse(0);
                sql.append(String.format("ALTER TABLE %s ALTER COLUMN id RESTART WITH %d;\n", entry.getKey(), maxId + 1));
            }
        }

        return sql.toString();
    }

    @Nullable
    private Long id(@Nullable BaseEntity entity){
        return entity != null ? entity.getId() : null;
    }

    private <T extends BaseEntity> List<T> exportAllEntities(StringBuilder sql, String header, JpaRepository<T,?> repository, Function<T, String> row) {
        var list = repository.findAll();
        if (!list.isEmpty()) {
            sql.append(header).append("\n");
            for (var it = list.iterator(); it.hasNext(); ) {
                var t = it.next();
                var line = row.apply(t);
                sql.append(line);
                sql.append(it.hasNext() ? "," : ";");
                sql.append("\n");
            }
        }
        sql.append("\n");
        return list;
    }

    private String escapeSql(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("'", "''");
    }
}

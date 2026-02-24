INSERT INTO instrument
(id, name) VALUES
(1, 'Guitar'),
(2, 'Bass'),
(3, 'Drums');

INSERT INTO tuning
(id, name, instrument_id) VALUES
(1, 'EADG', 2),
(2, 'DADG', 2),
(3, 'CFCG', 2),
(4, 'DGCF', 2),
(5, 'BEADG', 2),
(6, 'BEAD', 2),
(7, 'EAdgbe', 1 );

INSERT INTO artist
(id, name) VALUES
(1, 'Muse'),
(2, 'U2'),
(3, 'Phil Collins'),
(4, 'Blondie'),
(5, 'Arctic Monkeys');

INSERT INTO song
(id, name, artist_id) VALUES
(1, 'Sunday Bloody Sunday', 2),
(2, 'Call Me', 4),
(3, 'Do I Wanna Know?', 5);

INSERT INTO y_channel
(id, name, youtube_id) VALUES
(1, 'CoverSolutions', 'CoverSolutions'),
(2, 'Harry - Music & Stuff', 'HarryMusicStuff');

INSERT INTO y_video
(id, youtube_id, channel_id, song_id, instrument_id, tuning_id) VALUES
(1, 'QDka2OB06LE', 1, 1, 2, 1),
(2, 'G-LuMZo9ECk', 2, 2, 2, 1),
(3, 'G-jG6HZLd_xrE', 1, 3, 2, 1),
(4, 'G-LuMZo9ECk', 2, 3, 2, 1);

-- Reset sequences to avoid primary key conflicts
ALTER TABLE artist ALTER COLUMN id RESTART WITH 6;
ALTER TABLE song ALTER COLUMN id RESTART WITH 4;
ALTER TABLE y_channel ALTER COLUMN id RESTART WITH 3;
ALTER TABLE y_video ALTER COLUMN id RESTART WITH 5;
ALTER TABLE tuning ALTER COLUMN id RESTART WITH 8;
ALTER TABLE instrument ALTER COLUMN id RESTART WITH 4;
package com.github.kojotak.bassbook.claude.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YouTubeServiceTest {

    YouTubeService service = new YouTubeService();

    @Test
    public void parseExampleYoutubeUrl() throws Exception {
        var parsed = service.processUrl("https://www.youtube.com/watch?v=90zm4XDAtDo");
        assertEquals("90zm4XDAtDo", parsed.videoId());
        assertEquals("Led Zeppelin - The Ocean (Bass cover with tabs)", parsed.title());
        assertEquals("basscination", parsed.channelId());
        assertEquals("Tom Bornemann", parsed.channelName());
    }
}
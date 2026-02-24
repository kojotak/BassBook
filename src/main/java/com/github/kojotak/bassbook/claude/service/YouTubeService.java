package com.github.kojotak.bassbook.claude.service;

import com.github.kojotak.bassbook.claude.model.VideoMetadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class YouTubeService {

    private static final Pattern YOUTUBE_PATTERN = Pattern.compile(
            "^(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]{11}).*$"
    );

    public VideoMetadata processUrl(String url) throws Exception {
        // Check if URL is a YouTube video
        var matcher = YOUTUBE_PATTERN.matcher(url);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unsupported URL: Only YouTube videos are supported");
        }

        var videoId = matcher.group(4);

        // Fetch the YouTube page
        var doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .get();

        // Extract metadata from the page
        var title = extractTitle(doc);
        var channelName = extractAuthor(doc);
        var channelId = extractChannelId(doc);

        if (videoId == null || title == null || channelName == null || channelId == null) {
            throw new Exception("Failed to extract video metadata");
        }

        return new VideoMetadata(videoId, title, channelName, channelId);
    }

    private String extractTitle(Document doc) {
        // Try multiple methods to extract title
        
        // Method 1: meta tag
        var metaTitle = doc.select("meta[name=title]").first();
        if (metaTitle != null && metaTitle.attr("content") != null) {
            return metaTitle.attr("content");
        }

        // Method 2: og:title
        var ogTitle = doc.select("meta[property=og:title]").first();
        if (ogTitle != null && ogTitle.attr("content") != null) {
            return ogTitle.attr("content");
        }

        // Method 3: title tag
        var titleTag = doc.select("title").first();
        if (titleTag != null) {
            var title = titleTag.text();
            // Remove " - YouTube" suffix if present
            return title.replace(" - YouTube", "");
        }

        return null;
    }

    private String extractAuthor(Document doc) {
        // Try multiple methods to extract author/channel name
        
        // Method 1: link[itemprop=name]
        var authorLink = doc.select("link[itemprop=name]").first();
        if (authorLink != null && authorLink.attr("content") != null) {
            return authorLink.attr("content");
        }

        // Method 2: span[itemprop=author] or meta
        var authorMeta = doc.select("span[itemprop=author] link[itemprop=name]").first();
        if (authorMeta != null && authorMeta.attr("content") != null) {
            return authorMeta.attr("content");
        }

        // Method 3: Try to find in JSON-LD script
        var scripts = doc.select("script");
        for (var script : scripts) {
            var scriptContent = script.html();
            if (scriptContent.contains("\"author\"")) {
                try {
                    // Simple extraction - look for author name pattern
                    var authorPattern = Pattern.compile("\"author\"\\s*:\\s*\"([^\"]+)\"");
                    var matcher = authorPattern.matcher(scriptContent);
                    if (matcher.find()) {
                        return matcher.group(1);
                    }
                } catch (Exception e) {
                    // Continue to next method
                }
            }
        }

        return null;
    }

    private String extractChannelId(Document doc) {
        var authorUrl = doc.select("span[itemprop=author] link[itemprop=url]").first();
        if (authorUrl != null && authorUrl.attr("href") != null) {
            var href = authorUrl.attr("href");
            var position = href.indexOf("@");
            return position > 0 ? href.substring(position + 1) : href;
        }

        return null;
    }
}

package com.github.kojotak.bassbook.claude.controller;

import com.github.kojotak.bassbook.claude.service.ExportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;

@Controller
public class ExportController {

    private final ExportService databaseExportService;

    public ExportController(ExportService databaseExportService) {
        this.databaseExportService = databaseExportService;
    }

    @PostMapping("/export")
    public ResponseEntity<Resource> exportDatabase() {
        try {
            // Generate SQL export content
            var sqlContent = databaseExportService.generateSqlExport();

            // Convert to bytes for download
            var data = sqlContent.getBytes(StandardCharsets.UTF_8);
            var resource = new ByteArrayResource(data);

            // Return as downloadable file
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.sql")
                    .contentType(MediaType.parseMediaType("application/sql"))
                    .contentLength(data.length)
                    .body(resource);
        } catch (Exception e) {
            // Return error response
            var errorContent = "-- Error exporting database: " + e.getMessage();
            var data = errorContent.getBytes(StandardCharsets.UTF_8);
            var resource = new ByteArrayResource(data);

            return ResponseEntity.internalServerError()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=error.txt")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(data.length)
                    .body(resource);
        }
    }
}

package com.github.kojotak.bassbook.claude.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExportServiceTest {

    @Autowired
    private ExportService exporter;

    @Test
    public void testExportDatabase() {
        var export = exporter.generateSqlExport();
        var instrumentsFragment = """
                INSERT INTO instrument
                (id, name) VALUES
                (1, 'Guitar'),
                (2, 'Bass'),
                (3, 'Drums');
                """;
        assertTrue(export.contains(instrumentsFragment));
    }
}
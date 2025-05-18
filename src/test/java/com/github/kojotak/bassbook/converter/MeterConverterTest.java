package com.github.kojotak.bassbook.converter;

import com.github.kojotak.bassbook.data.Meter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeterConverterTest {

    private final MeterToStringConverter toString = new MeterToStringConverter();
    private final StringToMeterConverter toMeter = new StringToMeterConverter();

    @Test
    public void testSymetry() {
        var meter = new Meter(12, 8);
        var string = toString.convert(meter);
        var converted = toMeter.convert(string);
        assertEquals(meter, converted);
    }

    @Test
    public void testToString() {
        var meter = new Meter(5, 4);
        var string = toString.convert(meter);
        assertEquals("5_4", string);
    }

    @Test
    public void testToMeter() {
        var meter = toMeter.convert("3_4");
        assertEquals(new Meter(3, 4), meter);
    }
}
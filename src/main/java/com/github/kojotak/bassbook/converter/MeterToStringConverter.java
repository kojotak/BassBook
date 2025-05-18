package com.github.kojotak.bassbook.converter;

import com.github.kojotak.bassbook.data.Meter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MeterToStringConverter implements Converter<Meter, String> {

    @Override
    @Nullable
    public String convert(@Nullable Meter meter) {
        if (meter == null) {
            return null;
        } else {
            return meter.count() + "_" + meter.perBeat();
        }
    }
}

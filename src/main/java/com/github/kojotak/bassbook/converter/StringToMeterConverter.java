package com.github.kojotak.bassbook.converter;

import com.github.kojotak.bassbook.data.Meter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringToMeterConverter implements Converter<String, Meter> {

    @Override
    @Nullable
    public Meter convert(@Nullable String source) {
        if (!StringUtils.hasText(source)) {
            return null;
        }
        var parts = source.split("_");
        if (parts.length == 2) {
            return new Meter(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else {
            throw new IllegalArgumentException("Can not create Meter from " + source);
        }
    }
}

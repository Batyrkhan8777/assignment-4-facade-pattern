package com.example.mediaconverter.subsystem;

import java.util.Locale;
import java.util.Set;

/** Selects an output codec based on its format name. */
public final class CodecFactory {
    private static final Set<String> SUPPORTED_FORMATS = Set.of("mp3", "aac", "wav");

    public Codec codecFor(String format) {
        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Target format must not be blank");
        }
        String normalizedFormat = format.toLowerCase(Locale.ROOT);
        if (!SUPPORTED_FORMATS.contains(normalizedFormat)) {
            throw new IllegalArgumentException("Unsupported target format: " + format);
        }
        return () -> normalizedFormat;
    }
}

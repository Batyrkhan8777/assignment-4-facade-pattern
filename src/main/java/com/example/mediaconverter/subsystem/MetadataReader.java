package com.example.mediaconverter.subsystem;

import java.util.Locale;
import java.util.Set;

/** Reads simulated metadata from a file name. */
public final class MetadataReader {
    private static final Set<String> SUPPORTED_FORMATS = Set.of("wav", "mp3", "aac");

    public MediaFile read(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Input file name must not be blank");
        }
        int extensionStart = fileName.lastIndexOf('.');
        if (extensionStart <= 0 || extensionStart == fileName.length() - 1) {
            throw new IllegalArgumentException("Input file must have a supported extension: " + fileName);
        }

        String format = fileName.substring(extensionStart + 1).toLowerCase(Locale.ROOT);
        if (!SUPPORTED_FORMATS.contains(format)) {
            throw new IllegalArgumentException("Unsupported input format: " + format);
        }

        // Deterministic sample metadata keeps the example runnable without media libraries.
        return new MediaFile(fileName, format, 320, 245, false);
    }
}

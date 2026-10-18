package com.example.mediaconverter.subsystem;

/** Immutable metadata used by the simulated conversion pipeline. */
public record MediaFile(
        String name,
        String format,
        int bitrateKbps,
        int durationSeconds,
        boolean normalized) {

    public MediaFile {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("File name must not be blank");
        }
        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Format must not be blank");
        }
        if (bitrateKbps < 0 || durationSeconds < 0) {
            throw new IllegalArgumentException("Bitrate and duration must not be negative");
        }
    }
}

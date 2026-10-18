package com.example.mediaconverter.subsystem;

/** Simulates decoding into a standard intermediate bitrate. */
public final class BitrateReader {
    public MediaFile read(MediaFile source, Codec targetCodec) {
        if (source == null || targetCodec == null) {
            throw new IllegalArgumentException("Source media and target codec are required");
        }
        return new MediaFile(source.name(), targetCodec.format(), 256,
                source.durationSeconds(), source.normalized());
    }
}

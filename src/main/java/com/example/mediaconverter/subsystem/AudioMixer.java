package com.example.mediaconverter.subsystem;

/** Simulates audio normalization and final packaging. */
public final class AudioMixer {
    public MediaFile mix(MediaFile decodedMedia, Codec targetCodec) {
        if (decodedMedia == null || targetCodec == null) {
            throw new IllegalArgumentException("Decoded media and target codec are required");
        }
        return new MediaFile(withTargetExtension(decodedMedia.name(), targetCodec.format()),
                targetCodec.format(), decodedMedia.bitrateKbps(),
                decodedMedia.durationSeconds(), true);
    }

    private String withTargetExtension(String fileName, String targetFormat) {
        int extensionStart = fileName.lastIndexOf('.');
        String baseName = extensionStart > 0 ? fileName.substring(0, extensionStart) : fileName;
        return baseName + "." + targetFormat;
    }
}

package com.example.mediaconverter.client;

import com.example.mediaconverter.facade.MediaConversionFacade;
import com.example.mediaconverter.subsystem.MediaFile;

/** Demonstrates that a client needs to know only the facade. */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        MediaConversionFacade converter = new MediaConversionFacade();
        String inputName = args.length > 0 ? args[0] : "concert.wav";
        String targetFormat = args.length > 1 ? args[1] : "mp3";

        MediaFile result = converter.convert(inputName, targetFormat);
        System.out.printf("Converted media: %s | format=%s | bitrate=%d kbps | duration=%d s | normalized=%s%n",
                result.name(), result.format(), result.bitrateKbps(),
                result.durationSeconds(), result.normalized());
    }
}

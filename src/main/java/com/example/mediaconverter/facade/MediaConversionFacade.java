package com.example.mediaconverter.facade;

import com.example.mediaconverter.subsystem.AudioMixer;
import com.example.mediaconverter.subsystem.BitrateReader;
import com.example.mediaconverter.subsystem.Codec;
import com.example.mediaconverter.subsystem.CodecFactory;
import com.example.mediaconverter.subsystem.MediaFile;
import com.example.mediaconverter.subsystem.MetadataReader;

/** Simplifies access to the media conversion subsystem. */
public final class MediaConversionFacade {
    private final MetadataReader metadataReader;
    private final CodecFactory codecFactory;
    private final BitrateReader bitrateReader;
    private final AudioMixer audioMixer;

    public MediaConversionFacade() {
        this(new MetadataReader(), new CodecFactory(), new BitrateReader(), new AudioMixer());
    }

    public MediaConversionFacade(MetadataReader metadataReader, CodecFactory codecFactory,
                                 BitrateReader bitrateReader, AudioMixer audioMixer) {
        this.metadataReader = requireDependency(metadataReader, "metadataReader");
        this.codecFactory = requireDependency(codecFactory, "codecFactory");
        this.bitrateReader = requireDependency(bitrateReader, "bitrateReader");
        this.audioMixer = requireDependency(audioMixer, "audioMixer");
    }

    /** Converts the simulated media file and returns its resulting metadata. */
    public MediaFile convert(String inputName, String targetFormat) {
        MediaFile source = metadataReader.read(inputName);
        Codec targetCodec = codecFactory.codecFor(targetFormat);
        MediaFile decoded = bitrateReader.read(source, targetCodec);
        return audioMixer.mix(decoded, targetCodec);
    }

    private static <T> T requireDependency(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        return dependency;
    }
}

package com.kronae.kixel;

import com.kronae.kixel.api.KixelVideoEncoder;
import org.jcodec.common.Codec;
import org.jcodec.common.Format;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class KixelVideo {
    private @NotNull ArrayList<@NotNull Kixel> frames;
    public KixelVideo() {
        frames = new ArrayList<>();
    }
    public KixelVideo(@NotNull ArrayList<@NotNull Kixel> frames) {
        this.frames = frames;
    }
    public @NotNull ArrayList<Kixel> getFrames() {
        return frames;
    }
    public @Range(from=0, to=Integer.MAX_VALUE) int getFrameLength() {
        return frames.toArray().length;
    }

    public KixelVideo setFrames(@NotNull ArrayList<Kixel> kixels) {
        frames = kixels;
        return this;
    }
    public KixelVideo addFrame(@NotNull Kixel... kixel) {
        frames.addAll(List.of(kixel));
        return this;
    }
    public KixelVideo setFrame(int index, @NotNull Kixel frame) {
        frames.set(index, frame);
        return this;
    }
    public void save(@Range(from=1, to=Integer.MAX_VALUE) int fps, File outputFile, @Nullable Consumer<@NotNull Integer> consumer, @NotNull Format format, @NotNull Codec videoCodec, @Nullable Codec audioCodec) throws IOException {
        new KixelVideoEncoder(fps, frames).encode(outputFile, consumer, format, videoCodec, audioCodec);
    }
}

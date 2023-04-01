package com.kronae.kixel.api;

import com.kronae.kixel.Kixel;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.Codec;
import org.jcodec.common.Format;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;
import org.jcodec.scale.AWTUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public record KixelVideoEncoder(@Range(from = 1, to = Integer.MAX_VALUE) int fps, @NotNull ArrayList<Kixel> frames) {
    public void encode(@NotNull File outputFile, @Nullable Consumer<@NotNull Integer> consumer, @NotNull Format format, @NotNull Codec videoCodec, @Nullable Codec audioCodec) throws IOException {
        SeekableByteChannel channel = new FileChannelWrapper((new FileOutputStream(outputFile)).getChannel());
        SequenceEncoder encoder = new SequenceEncoder(channel, new Rational(fps, 1), format, videoCodec, audioCodec);

        for (int offset = 0; offset < frames.toArray().length; offset++) {
            Kixel kixel = frames.get(offset);
            BufferedImage frame = kixel.getBufferedImage();
            encoder.encodeNativeFrame(AWTUtil.fromBufferedImageRGB(frame));
            if (consumer != null) consumer.accept(offset);
        }

        encoder.finish();
        NIOUtils.closeQuietly(channel);
    }
}

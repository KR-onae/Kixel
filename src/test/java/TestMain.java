import com.kronae.kixel.Kixel;
import com.kronae.kixel.KixelVideo;
import com.kronae.kixel.pixel.RGB;
import org.jcodec.common.Codec;
import org.jcodec.common.Format;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TestMain {
    public static void main(String[] args) throws IOException {
        Kixel one = Kixel.read(Objects.requireNonNull(TestMain.class.getResource("asdf.png")).openStream()).asRGBKixel(RGB.WHITE);
        Kixel two = Kixel.read(Objects.requireNonNull(TestMain.class.getResource("two2.png")).openStream()).asRGBKixel(RGB.WHITE);
        KixelVideo video = new KixelVideo().addFrame(one, two);
        video.save(2, new File("C:\\Users\\mcnam\\OneDrive\\바탕 화면\\asdf.mkv"), null, Format.MKV, Codec.H264, null);
    }
}

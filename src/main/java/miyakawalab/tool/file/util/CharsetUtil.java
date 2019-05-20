package miyakawalab.tool.file.util;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CharsetUtil {
    private CharsetUtil() {
    }

    public static String toUtf8Base64(String base64) throws IOException {
        byte[] before = Base64Util.decode(base64);
        return Base64Util.encode(toUtf8ByteArray(before));
    }

    public static InputStream toUtf8InputStream(InputStream before) throws IOException {
        byte[] bytes = IOUtils.toByteArray(before);
        return toUtf8InputStream(bytes);
    }

    public static InputStream toUtf8InputStream(byte[] before) throws IOException {
        Charset encoding = CharsetEnum.getEncoding(before).getEncoding();
        List<String> lines = IOUtils.readLines(new ByteArrayInputStream(before), encoding);
        return IOUtils.toInputStream(String.join("\n", lines), CharsetEnum.UTF_8.getEncoding());
    }

    public static byte[] toUtf8ByteArray(byte[] before) throws IOException {
        InputStream after = toUtf8InputStream(before);
        return IOUtils.toByteArray(after);
    }

    private enum CharsetEnum {
        UTF_8 {
            @Override
            public Charset getEncoding() {
                return StandardCharsets.UTF_8;
            }
        },
        WINDOWS_31J {
            @Override
            public Charset getEncoding() {
                return Charset.forName("WINDOWS-31J");
            }
        };

        public final boolean encodingCheck(byte[] src) {
            byte[] tmp = new String(src, this.getEncoding()).getBytes(this.getEncoding());
            return Arrays.equals(tmp, src);
        }

        private static CharsetEnum getEncoding(byte[] src) {
            return Arrays.stream(values())
                .filter(charsetEnum -> charsetEnum.encodingCheck(src))
                .findFirst().orElse(CharsetEnum.UTF_8);
        }

        public abstract Charset getEncoding();
    }
}

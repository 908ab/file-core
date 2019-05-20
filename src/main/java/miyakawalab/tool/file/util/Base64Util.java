package miyakawalab.tool.file.util;

import java.util.Base64;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Util {
    private final static Pattern PATTERN = Pattern.compile("data:(.*);base64,(.*)");

    private Base64Util() {}

    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static String encode(byte[] decoded) {
        return Base64.getEncoder().encodeToString(decoded);
    }

    public static String getContentTypeString(String encoded) {
        return getGroupString(encoded, 1).orElse("");
    }

    public static String getBase64String(String encoded) {
        return getGroupString(encoded, 2).orElse("");
    }

    private static Optional<String> getGroupString(String str, int groupIndex) {
        Matcher matcher = PATTERN.matcher(str);
        if (matcher.find()) {
            return Optional.of(matcher.group(groupIndex));
        }
        return Optional.empty();
    }
}
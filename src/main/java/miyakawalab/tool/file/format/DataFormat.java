package miyakawalab.tool.file.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface DataFormat {
    InputStream toXml() throws IOException;
    InputStream toJson() throws IOException;
    List<Set<?>> getHeaderSets();
}

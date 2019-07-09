package miyakawalab.tool.file.format;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonFormat extends AbstractDataFormat {
    public JsonFormat(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}

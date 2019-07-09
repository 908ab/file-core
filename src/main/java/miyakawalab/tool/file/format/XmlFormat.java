package miyakawalab.tool.file.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;

public class XmlFormat extends AbstractDataFormat {
    public XmlFormat(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return new XmlMapper();
    }
}

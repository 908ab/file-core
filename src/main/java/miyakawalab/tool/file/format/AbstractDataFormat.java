package miyakawalab.tool.file.format;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractDataFormat implements DataFormat {
    private List<Map<?, ?>> mappingList;

    AbstractDataFormat(InputStream inputStream) throws IOException {
        MappingIterator<Map<?, ?>> mappingIterator = this.getObjectReader().readValues(inputStream);
        this.mappingList = mappingIterator.readAll();
    }

    @Override
    public final InputStream toXml() throws IOException {
        byte[] bytes = new XmlMapper().writeValueAsBytes(this.mappingList);
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public final InputStream toJson() throws IOException {
        byte[] bytes = new ObjectMapper().writeValueAsBytes(this.mappingList);
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public final List<Set<?>> getHeaderSets() {
        return this.mappingList.stream()
            .map(Map::keySet)
            .collect(Collectors.toList());
    }

    protected abstract ObjectMapper getObjectMapper();

    protected ObjectReader getObjectReader() {
        return this.getObjectMapper().readerFor(Map.class);
    }
}

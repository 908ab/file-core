package miyakawalab.tool.file.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;

public class CsvFormat extends AbstractDataFormat {
    public CsvFormat(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return new CsvMapper();
    }

    @Override
    protected ObjectReader getObjectReader() {
        return super.getObjectReader().with(CsvSchema.emptySchema().withHeader());
    }
}

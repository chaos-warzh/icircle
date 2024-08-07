package github.chaoswarzh.icircle.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class MillisSerializerUtil extends JsonSerializer<Date> {

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (value != null) {
      gen.writeNumber(value.getTime());
    }
  }
}

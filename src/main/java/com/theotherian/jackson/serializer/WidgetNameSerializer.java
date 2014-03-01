package com.theotherian.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.theotherian.jackson.dto.WidgetName;

public class WidgetNameSerializer extends JsonSerializer<WidgetName> {

  @Override
  public void serialize(WidgetName widgetName, JsonGenerator jgen, SerializerProvider provider) throws IOException,
      JsonProcessingException {
    jgen.writeString(widgetName.value());
  }

  
}

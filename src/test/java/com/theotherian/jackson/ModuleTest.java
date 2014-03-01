package com.theotherian.jackson;

import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.theotherian.jackson.dto.Widget;
import com.theotherian.jackson.dto.WidgetName;
import com.theotherian.jackson.serializer.WidgetNameSerializer;

public class ModuleTest {
  
  @Test
  public void failureWithException() throws Exception {
    Widget widget = createWidget();
    ObjectMapper mapper = new ObjectMapper();
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, widget);
    System.out.println(writer.toString());
  }
  
  @Test
  public void failureWithoutProperty() throws Exception {
    Widget widget = createWidget();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, widget);
    System.out.println(writer.toString());
  }
  
  @Test
  public void success() throws Exception {
    Widget widget = createWidget();
    ObjectMapper mapper = new ObjectMapper();
    Module serializerModule = new SimpleModule().addSerializer(WidgetName.class, new WidgetNameSerializer());
    mapper.registerModule(serializerModule);
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, widget);
    System.out.println(writer.toString());
  }

  private Widget createWidget() {
    Widget widget = new Widget();
    widget.setId(1);
    WidgetName name = new WidgetName();
    name.value("my widget");
    widget.setName(name);
    return widget;
  }

}

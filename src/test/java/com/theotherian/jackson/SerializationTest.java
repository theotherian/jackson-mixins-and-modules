package com.theotherian.jackson;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Lists;
import com.theotherian.jackson.dto.Thing;
import com.theotherian.jackson.dto.User;
import com.theotherian.jackson.mixin.ThingMixin;

public class SerializationTest {
  
  @Test
  public void failure() throws Exception {
    User user = createUser();
    ObjectMapper mapper = new ObjectMapper();
    
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, user);
  }
  
  @Test
  public void success() throws Exception {
    User user = createUser();
    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixInAnnotations(Thing.class, ThingMixin.class);
    FilterProvider filterProvider = new SimpleFilterProvider()
      .addFilter("thing filter", SimpleBeanPropertyFilter.serializeAllExcept("user"));
    mapper.setFilters(filterProvider);
    
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, user);
  }

  private User createUser() {
    User user = new User();
    user.setName("me");
    Thing thing = new Thing();
    thing.setName("that");
    thing.setUser(user);
    user.setThings(Lists.newArrayList(thing));
    return user;
  }

}

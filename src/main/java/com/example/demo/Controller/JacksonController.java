package com.example.demo.Controller;

import com.example.demo.Bean.MyUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JacksonController {
  @Autowired ObjectMapper mapper;

  @GetMapping("/getuser")
  public MyUser getUser() {
    MyUser user = new MyUser();
    user.setUserName("mrbird");
    user.setAdd_date(new Date());
    return user;
  }

  @GetMapping("/serialization")
  public String serialization() {
    try {
      MyUser user = new MyUser();
      user.setUserName("mrbird");
      user.setAdd_date(new Date());
      String str = mapper.writeValueAsString(user);
      return str;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @GetMapping("/readjsonstring")
  public String readJsonString() {
    try {
      String json = "{\"name\":\"mrbird\",\"age\":26}";
      JsonNode node = this.mapper.readTree(json);
      String name = node.get("name").asText();
      int age = node.get("age").asInt();
      return name + " " + age;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @GetMapping("/readjsonasobject")
  public String readJsonAsObject() {
    try {
      String json = "{\"userName\":\"mrbird\",\"age\":26}";
      MyUser user = mapper.readValue(json, MyUser.class);
      String name = user.getUserName();
      int age = user.getAge();
      return name + " " + age;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}

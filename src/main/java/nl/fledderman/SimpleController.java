package nl.fledderman;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

  private final ConfigServerProperties properties;

  public SimpleController(ConfigServerProperties myServerProperties) {
    this.properties = myServerProperties;
  }

  @GetMapping("/message")
  public String getFeatures() {
    System.out.println("showing message: "+ properties.getMessage());
    return properties.getMessage();
  }
}

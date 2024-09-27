package nl.fledderman;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Component
public class ConfigRefresher {

  private final String refreshUrl = "http://localhost:8080/actuator/refresh";

  private final RestTemplate restTemplate = new RestTemplate();

  @Scheduled(fixedRate = 30000)
  public void refreshConfig() {
    try {
      restTemplate.exchange(refreshUrl, HttpMethod.POST, getRequest(), String.class);
    } catch (Exception e) {
      System.err.println("Error refreshing config: " + e.getMessage());
    }
  }

  private HttpEntity<String> getRequest() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    return new HttpEntity<>(headers);
  }
}

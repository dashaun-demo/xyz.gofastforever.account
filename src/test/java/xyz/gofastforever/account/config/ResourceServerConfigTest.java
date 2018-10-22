package xyz.gofastforever.account.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceServerConfigTest {

  @LocalServerPort
  private int port;

  private TestRestTemplate template = new TestRestTemplate();

  @Test
  public void resourceLoads() {
    ResponseEntity<String> response = template
        .getForEntity("http://localhost:{port}/", String.class, port);
    Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    String auth = response.getHeaders().getFirst("WWW-Authenticate");
    Assert.assertTrue("Wrong header: " + auth, auth.startsWith("Bearer"));
  }
}
package net.cyphoria.webservice.superclient;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class BasicAuthTest {

    private final TestRestTemplate restTemplate =
            new TestRestTemplate("user2", "pw2");


    @Test
    @Ignore
    public void basicAuthWorks() throws Exception {
        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void basicAuthWorks2() throws Exception {
        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/foo", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("Foo works: Hello user2"));
    }


}

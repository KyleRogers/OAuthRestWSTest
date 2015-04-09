package net.cyphoria.webservice.superclient;

import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class NoAuthTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void fooIsSecured() {
        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/foo", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void indexIsSecured() {
        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void usersIsSecured() {
        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/users", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

}

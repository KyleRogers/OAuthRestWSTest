package net.cyphoria.webservice.superclient;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */

public class OAuthTest {


    {
        final ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setClientSecret("123456");
        resource.setClientId("clientapp");
        resource.setAccessTokenUri("http://localhost:8082/oauth/token");

        restTemplate = new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext());
    }

    private OAuth2RestTemplate restTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void accessVioOAuth() {

        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("OAuth works: Hello clientapp"));
    }


    @Test
    public void accessUserOnlyResource() {
        expectedException.expect(UserDeniedAuthorizationException.class);

        restTemplate.getForEntity("http://localhost:8082/posts", String.class);

    }

}

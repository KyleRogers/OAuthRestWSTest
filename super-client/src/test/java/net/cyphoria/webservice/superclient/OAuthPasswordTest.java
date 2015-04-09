package net.cyphoria.webservice.superclient;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class OAuthPasswordTest {

    {
        final ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setClientSecret("123456");
        resource.setClientId("clientapp");
        resource.setUsername("user2");
        resource.setPassword("pw2");
        resource.setScope(asList("read"));
        resource.setAccessTokenUri("http://localhost:8082/oauth/token");

        ResourceOwnerPasswordAccessTokenProvider p = new ResourceOwnerPasswordAccessTokenProvider();
        final OAuth2AccessToken accessToken = p.obtainAccessToken(resource, new DefaultAccessTokenRequest());
        restTemplate = new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(accessToken));
    }

    private OAuth2RestTemplate restTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void accessVioOAuth() {

        final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/posts", String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("Posts works: Hello user2 via clientapp"));
    }

    @Test
    public void accessClientOnlyResource() {
        expectedException.expect(UserDeniedAuthorizationException.class);

        restTemplate.getForEntity("http://localhost:8082/users", String.class);
    }



}

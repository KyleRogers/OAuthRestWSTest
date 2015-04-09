package net.cyphoria.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Vielleicht funktioniert http://stackoverflow.com/questions/27917004/spring-secuirty-oauth-2-multiple-user-authentication-services
 * oder
 * http://jaxenter.com/rest-api-spring-java-8-112289.html
 *
 *
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@SpringBootApplication
@RestController
public class OAuthWS {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {

        String clientId = "-none-";
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            clientId = authentication.getOAuth2Request().getClientId();
        }

        return "OAuth works: Hello " + clientId;
    }

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    public String indexFoo(Principal principal) {

        return "Foo works: Hello " + principal.getName();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String indexUsers() {
        return "Users works";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String indexposts(Principal principal) {


        String clientId;
        String user;

        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        clientId = authentication.getOAuth2Request().getClientId();
        user = authentication.getName();


        return "Posts works: Hello " + user + " via " + clientId;
    }



    public static void main(String... args) {
        SpringApplication.run(OAuthWS.class, args);
    }
}

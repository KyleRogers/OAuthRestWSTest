package net.cyphoria.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@SpringBootApplication
@RestController
public class BasicAuthWS {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getContent() {
        return "Success";
    }

    public static void main(String... args) {
        SpringApplication.run(BasicAuthWS.class, args);
    }


}

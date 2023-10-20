package cloudcode.helloworld.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggedController {

    @GetMapping("/logged-google")
    public void loggedGoogle() {

    }

    @GetMapping("/logged-github")
    public void loggedGithub() {

    }
}

package cloudcode.helloworld.web;

import cloudcode.helloworld.app.FakeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${${sm://application-fake}:DEFAULT}")
    private String applicationFake;

    @Value("${${sm://github-credentials}:}")
    private String githubCredentials;

    @GetMapping("/test")
    public FakeResponse getTestResponse() {
        return new FakeResponse();
    }

    @GetMapping("/test2")
    public String getTest2Response() {
        return applicationFake;
    }

    @GetMapping("/test3")
    public String getTest3Response() {
        return githubCredentials;
    }
}

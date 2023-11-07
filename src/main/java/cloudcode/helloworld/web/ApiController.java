package cloudcode.helloworld.web;

import cloudcode.helloworld.app.FakeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/test")
    public FakeResponse getTestResponse() {
        return new FakeResponse();
    }
}

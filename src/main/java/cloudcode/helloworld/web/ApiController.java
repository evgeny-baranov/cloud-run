package cloudcode.helloworld.web;

import cloudcode.helloworld.app.FakeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/test")
    public FakeResponse getTestResponse() {
        logger.info("getTestResponse");

        return new FakeResponse();
    }
}

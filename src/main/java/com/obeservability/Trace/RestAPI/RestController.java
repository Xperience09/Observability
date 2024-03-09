package com.obeservability.Trace.RestAPI;


import com.obeservability.Trace.Config.RateLimited;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    String className = RestController.class.getName();
    private Logger logger = LoggerFactory.getLogger(RestController.class);

    private final Bucket bucket;

    public RestController() {
        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofSeconds(60)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

//    @RateLimited(rate = 1, seconds = 60)
    @GetMapping(path = "/hello")
    public ResponseEntity<String> hello() {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Hello");
        }
        logger.info("{} : {}",className,HttpStatus.TOO_MANY_REQUESTS);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping(path = "/bye")
    public ResponseEntity<String> bye() {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Bye");
        }
        logger.info("{} : {}",className,HttpStatus.TOO_MANY_REQUESTS);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

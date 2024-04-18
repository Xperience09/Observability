package com.obeservability.Trace.RestAPI;


import com.obeservability.Trace.dtoServices.DataProcess;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;


@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {
    String className = RestController.class.getName();

    private final Bucket bucket;

    public RestController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(60)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
        long remaining = this.bucket.getAvailableTokens();
        log.info("Remaining tokens: {}", remaining);
    }

    //    @RateLimited(rate = 1, seconds = 60)
    @GetMapping(path = "/triggerData")
    public ResponseEntity<String> triggerData() {
        log.info("{} : API=triggerData()", className);
        if (bucket.tryConsume(1)) {
            DataProcess dataProcess = new DataProcess();
            dataProcess.dataFillup();
            return ResponseEntity.ok("Hello");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> triggerData2() {
        log.info("{} : API=triggerData2()", className);
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Bye");
        }
        log.info("{} : {}", className, HttpStatus.TOO_MANY_REQUESTS);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

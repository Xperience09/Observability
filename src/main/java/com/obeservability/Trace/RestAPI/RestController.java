package com.obeservability.Trace.RestAPI;


import com.obeservability.Trace.dtoServices.DataProcess;
import com.obeservability.Trace.dtoServices.ProcessTwo;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;


@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {
    String className = RestController.class.getName();

//    private final Bucket bucket;
    private final Tracer tracer;
    @Autowired
    DataProcess dataProcess;
    io.opentelemetry.context.Context context = Context.current();

    public RestController(OpenTelemetry openTelemetry) {
        tracer=openTelemetry.getTracer(RestController.class.getName());
//        Bandwidth limit = Bandwidth.classic(1000, Refill.greedy(1000, Duration.ofSeconds(1)));
//        this.bucket = Bucket.builder()
//                .addLimit(limit)
//                .build();
//        long remaining = this.bucket.getAvailableTokens();
//        log.info("Remaining tokens: {}", remaining);
    }

    //    @RateLimited(rate = 1, seconds = 60)
    @GetMapping(path = "/triggerData")
    public ResponseEntity<String> triggerData(@RequestParam("word") String word) {
        Span span = tracer.spanBuilder("triggerData").startSpan();
        try(Scope scope=span.makeCurrent()) {
            log.info("{} : API=triggerData()", className);
//            if (bucket.tryConsume(1)) {
//                dataProcess.dataFillup();
                return ResponseEntity.ok(String.valueOf(dataProcess.dataFillup(span)));
//            }
//            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }catch (Throwable t){
            span.recordException(t);
            throw t;
        }
        finally {
            span.end();
        }
    }

    @GetMapping(path = "/printWord")
    public ResponseEntity<String> printWord1(String word) {
        log.info("{} : API=triggerData2()", className);
//        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Okayyy");
//        }
//        log.info("{} : {}", className, HttpStatus.TOO_MANY_REQUESTS);
//        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

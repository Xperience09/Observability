package com.obeservability.Trace.dtoServices;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessTwo {
//    Tracer tracer;

    public String printWord(String word){
//        Span childSpan = tracer.spanBuilder("Child").startSpan();
        try {
            log.info(word);
            log.info("traceId={} span={}",Span.current().getSpanContext().getTraceId(),Span.current().getSpanContext().getSpanId());
            return "The word is: " + word;
        }finally {
//            childSpan.end();
        }
    }
}

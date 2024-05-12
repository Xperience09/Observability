package com.obeservability.Trace.dtoServices;

import com.obeservability.Trace.RestAPI.RestController;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessTwo {
    private Tracer tracer;
    ProcessTwo(OpenTelemetry openTelemetry){
        tracer=openTelemetry.getTracer(ProcessTwo.class.getName());
    }

    public String printWord(String word){
        Span childSpan = tracer.spanBuilder("Child").startSpan();

        try(Scope scope=childSpan.makeCurrent()){
            log.info("traceId={} span={} word={}",childSpan.getSpanContext().getTraceId(),childSpan.getSpanContext().getSpanId(),word);
            return word;
        }finally {
            childSpan.end();
        }
    }
}

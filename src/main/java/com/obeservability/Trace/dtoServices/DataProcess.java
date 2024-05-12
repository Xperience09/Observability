package com.obeservability.Trace.dtoServices;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
@Slf4j
public class DataProcess {
    private Tracer tracer;
    @Autowired
    private ProcessTwo processTwo;
    public DataProcess(OpenTelemetry openTelemetry){
        this.tracer=openTelemetry.getTracer(DataProcess.class.getName(),"0.1");
    }
    public DataProcess(){
        this(OpenTelemetry.noop());
    }

    public Map<String,String> dataFillup() {
        Span child = tracer.spanBuilder(getClass().getName()).startSpan();
        try(Scope scope=child.makeCurrent()) {
//    defining a map
            Map<String, String> map1 = new HashMap<>();

//    set up data
            map1.put("1", "A");
            map1.put("2", "B");
            map1.put("3", "B");

//            log.info(map1.toString());


//    using entry to obtain a set of key-value pairs
            Set<Map.Entry<String, String>> set1 = map1.entrySet();
//            log.info(set1.toString());

//    Stream

//      find single value/key from a map
            Optional<String> s = map1.entrySet()
                    .stream()
                    .filter(e -> "A".equals(e.getValue()))
                    .map(Map.Entry::getKey)
                    .findAny();
//            log.info("s1: " + s.get());  //this returned 1

//        find multiple results
            List<String> s2 = map1.entrySet().stream()
                    .filter(e -> "B".equals(e.getValue()))
                    .map(Map.Entry::getKey)
                    .toList();
            log.info("traceId={} span={} s2: {}",child.getSpanContext().getTraceId(),child.getSpanContext().getSpanId(), s2.toString());
            log.info(processTwo.printWord("hello"));
            return map1;
        }finally {
            child.end();
        }

    }
}

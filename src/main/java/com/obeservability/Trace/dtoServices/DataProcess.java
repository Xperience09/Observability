package com.obeservability.Trace.dtoServices;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DataProcess {

    public void dataFillup() {
//    defining a map
        Map<String, String> map1 = new HashMap<>();

//    set up data
        map1.put("1", "A");
        map1.put("2", "B");
        map1.put("3","B");

        log.info(map1.toString());


//    using entry to obtain a set of key-value pairs
        Set<Map.Entry<String, String>> set1 = map1.entrySet();
        log.info(set1.toString());

//    Stream

//      find single value/key from a map
        Optional<String> s = map1.entrySet()
                .stream()
                .filter(e -> "A".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .findAny();
        log.info("s1: "+s.get());  //this returned 1

//        find multiple results
        List<String> s2 = map1.entrySet().stream()
                .filter(e->"B".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .toList();
        log.info("s2: "+s2.toString());

    }
}

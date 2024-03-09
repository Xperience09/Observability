package com.obeservability.Trace.Config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Configuration
public class RateLimiter {

RateLimited rateLimited;
        private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

        @Around("execution(* com.example.demo.controller.*.*(..))")
        public Object limitRequestRate(ProceedingJoinPoint joinPoint) throws Throwable {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String key = className + "." + methodName;

            // Create a new bucket if it doesn't exist
            buckets.putIfAbsent(key, createNewBucket());

            Bucket bucket = buckets.get(key);

            // Try to consume a token from the bucket
            if (bucket.tryConsume(1)) {
                // Proceed with the method execution
                return joinPoint.proceed();
            } else {
                // Return an error response if rate limit exceeded
                throw new RuntimeException("Rate limit exceeded for method: " + methodName);
            }
        }

        private Bucket createNewBucket() {
            int rate = rateLimited.rate();
            long seconds = rateLimited.seconds();
            Bandwidth limit = Bandwidth.classic(rate, Refill.greedy(rate, Duration.ofSeconds(seconds)));
            return Bucket4j.builder().addLimit(limit).build();
        }
    }

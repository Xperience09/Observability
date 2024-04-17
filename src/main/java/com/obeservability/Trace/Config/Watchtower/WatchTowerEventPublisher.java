//package com.obeservability.Trace.Config.Watchtower;
//
//import io.cucumber.plugin.event.Event;
//import io.cucumber.plugin.event.EventPublisher;
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.lang3.StringUtils;
//
//@Slf4j
//public class WatchTowerEventPublisher {
//
//    WatchTowerEventPublisher(){
//        throw new IllegalStateException("Utility class");
//    }
//
//    private final EventPublisher eventPublisher = EventPublisherRegistry.lookup(WatchTowerEventPublisher.class);
//
//
//    public static void alert(String alert, String message, String details) {
//        try {
//            if (StringUtils.isBlank(alert)) alert = "Alert not set.";
//            if (StringUtils.isBlank(message)) message = "Message not set.";
//            if (StringUtils.isBlank(details)) details = "Details not set.";
//            log.error("Alert: {} ; Message: {} ; Details: {} ", alert, message, details);
//            Event e = new PwmCentralTriggerEvent(alert, message, details);
//            eventPublisher.publishAsync(e, new EventPublisher.ListenerAdapter() {
//                @Override
//                public void onFailedPublication(Rejection r) {
//                    log.error("Failed publication!! - {}, due to {}", r.event(), Throwables.getStackTraceAsString(r.cause()));
//                }
//
//                @Override
//                public void onSuccessfulPublication(Event e) {
//                    log.info("Published successfully - {}", e);
//                }
//            });
//        } catch (EventPublisher.EventPublicationException ex) {
//            log.error("Exception occurred, Message: {}, Reason: {}", ex.getMessage(), ex.getCause());
//        }
//    }
//}
//

package de.meisl.showcase.services;

import com.vaadin.flow.server.webpush.WebPush;
import com.vaadin.flow.server.webpush.WebPushMessage;
import com.vaadin.flow.server.webpush.WebPushSubscription;
import de.meisl.showcase.webpush.WebPushAction;
import de.meisl.showcase.webpush.WebPushOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebPushService {

    private final Map<String, WebPushSubscription> endpointToSubscription = new HashMap<>();
    WebPush webPush;
    @Value("${public.key}")
    private String publicKey;
    @Value("${private.key}")
    private String privateKey;
    @Value("${subject}")
    private String subject;

    /**
     * Initialize security and push service for initial get request.
     *
     */
    public WebPush getWebPush() {
        if (webPush == null) {
            webPush = new WebPush(publicKey, privateKey, subject);
        }
        return webPush;
    }

    /**
     * Send a notification to all subscriptions.
     *
     * @param title message title
     * @param body  message body
     */
    public void notifyAll(String title, String body) {
        endpointToSubscription.values().forEach(subscription -> {
            webPush.sendNotification(subscription, new WebPushMessage(title, body));
        });
    }

    public void sendNotification(String title, String message, WebPushAction webPushAction,
                                 String data, String icon) {
        WebPushOptions webPushOptions = new WebPushOptions(
                message,
                List.of(webPushAction),
                data,
                icon
        );
        new Thread(() -> endpointToSubscription.values().forEach(subscription -> webPush.sendNotification(
                subscription,
                new WebPushMessage(title, webPushOptions)))).start();
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger(WebPushService.class);
    }

    public void store(WebPushSubscription subscription) {
        getLogger().info("Subscribed to {}", subscription.endpoint());
        /*
         * Note, in a real world app you'll want to persist these
         * in the backend. Also, you probably want to know which
         * subscription belongs to which user to send custom messages
         * for different users. In this demo, we'll just use
         * endpoint URL as key to store subscriptions in memory.
         */
        endpointToSubscription.put(subscription.endpoint(), subscription);
    }


    public void remove(WebPushSubscription subscription) {
        getLogger().info("Unsubscribed {}", subscription.endpoint());
        endpointToSubscription.remove(subscription.endpoint());
    }

    public boolean isEmpty() {
        return endpointToSubscription.isEmpty();
    }
}
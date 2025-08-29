package de.meisl.showcase.webpush;

import java.io.Serializable;

public record WebPushAction(String action, String title) implements Serializable {
}

package com.copito.copbalance.security.application.events;

public interface EventPublisher<T> {
    void publish(T event);
}

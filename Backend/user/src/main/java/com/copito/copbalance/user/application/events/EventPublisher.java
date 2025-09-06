package com.copito.copbalance.user.application.events;

public interface EventPublisher<T> {
    void publish(T event);
}

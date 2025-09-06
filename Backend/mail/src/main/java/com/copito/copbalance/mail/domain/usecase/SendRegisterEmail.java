package com.copito.copbalance.mail.domain.usecase;

import com.copito.copbalance.mail.domain.event.UserCompletedEvent;

public interface SendRegisterEmail {
    void send(UserCompletedEvent event);
}

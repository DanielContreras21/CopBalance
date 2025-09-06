package com.copito.copbalance.mail.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompletedEvent {
    private String name;
    private String email;
}

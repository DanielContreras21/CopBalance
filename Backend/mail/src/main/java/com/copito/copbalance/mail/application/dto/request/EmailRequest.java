package com.copito.copbalance.mail.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    String to;
    String subject;
    String template;
    Map<String, Object> variables;
}

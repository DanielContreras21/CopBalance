package com.copito.copbalance.security.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneValidator {
    public static boolean isValidColombianPhone(String phone){
        String mobileRegex = "^3\\d{9}$";
        return phone != null && phone.matches(mobileRegex);
    }
}

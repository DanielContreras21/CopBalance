package com.copito.copbalance.user.infrastructure.client;

import com.copito.copbalance.user.infrastructure.http.response.AccountEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "security", url = "localhost:8090/auth/" )
public interface AccountClient {

    @GetMapping("/getEmail/{id}")
    AccountEmail getAccountEmail(@PathVariable String id);
}

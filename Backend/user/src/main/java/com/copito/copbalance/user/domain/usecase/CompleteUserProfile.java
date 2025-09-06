package com.copito.copbalance.user.domain.usecase;

import com.copito.copbalance.user.domain.model.entity.User;

public interface CompleteUserProfile {
    User complete(User user);
}

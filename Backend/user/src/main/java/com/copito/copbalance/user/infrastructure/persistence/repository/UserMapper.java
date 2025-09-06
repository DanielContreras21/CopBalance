package com.copito.copbalance.user.infrastructure.persistence.repository;

import com.copito.copbalance.user.domain.model.entity.User;
import com.copito.copbalance.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserEntity>{

    @Override
    public UserEntity toEntity(User user){
        return new UserEntity(
                user.getId(),
                user.getIdentificationType(),
                user.getIdentification(),
                user.getName(),
                user.getAddress()
        );
    }

    @Override
    public User toDomain(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getIdentificationType(),
                entity.getIdentification(),
                entity.getName(),
                entity.getAddress()
        );
    }
}

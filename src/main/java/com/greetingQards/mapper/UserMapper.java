package com.greetingQards.mapper;

import com.greetingQards.domain.User;
import com.greetingQards.repository.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}

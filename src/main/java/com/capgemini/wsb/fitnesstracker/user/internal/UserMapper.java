package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between User entities and User DTOs.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert.
     * @return the converted UserDto.
     */
    UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto to convert.
     * @return the converted User entity.
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
    }
}

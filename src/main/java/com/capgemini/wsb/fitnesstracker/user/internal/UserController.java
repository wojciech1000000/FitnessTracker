package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves all users in a simplified format.
     *
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping("/simple")
    public ResponseEntity<List<UserDto>> getAllSimpleUsers() {
        List<UserDto> users = userService.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the UserDto if found, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            UserDto userDto = userMapper.toDto(userService.getUserById(id));
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for users by email.
     *
     * @param email the email to search for.
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping("/email")
    public ResponseEntity<List<UserDto>> getUserByEmail(@RequestParam String email) {
        List<UserDto> users = userService.searchUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /**
     * Adds a new user.
     *
     * @param userDto the UserDto to add.
     * @return a ResponseEntity containing the created UserDto.
     */
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));
        return ResponseEntity.status(201).body(createdUser);
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update.
     * @param userDto the UserDto containing the updated data.
     * @return a ResponseEntity containing the updated UserDto.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
            return ResponseEntity.ok(userMapper.toDto(updatedUser));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return a ResponseEntity with status 204 if successful, or 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for users by email.
     *
     * @param email the email to search for.
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping("/search/email")
    public ResponseEntity<List<UserDto>> searchUsersByEmail(@RequestParam String email) {
        List<UserDto> users = userService.searchUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /**
     * Searches for users older than a specified age.
     *
     * @param age the age to compare against.
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping("/search/age")
    public ResponseEntity<List<UserDto>> searchUsersByAge(@RequestParam int age) {
        List<UserDto> users = userService.searchUsersByAgeGreaterThan(age)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users older than a specified date.
     *
     * @param time the date to compare against.
     * @return a ResponseEntity containing a list of UserDto.
     */
    @GetMapping("/older/{time}")
    public ResponseEntity<List<UserDto>> findUsersOlderThan(@PathVariable String time) {
        LocalDate date = LocalDate.parse(time);
        List<UserDto> users = userService.findUsersOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}

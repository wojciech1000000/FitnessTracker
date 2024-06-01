package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation for managing users.
 */
@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves all users.
     *
     * @return a list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the user with the specified ID.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create.
     * @return the created user.
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id   the ID of the user to update.
     * @param user the user data to update.
     * @return the updated user.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Searches for users by email.
     *
     * @param email the email to search for.
     * @return a list of users with emails containing the specified email.
     */
    @Override
    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }
    
    /**
     * Searches for users older than a specified age.
     *
     * @param age the age to compare against.
     * @return a list of users whose age is greater than the specified value.
     */
    @Override
    public List<User> searchUsersByAgeGreaterThan(int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    /**
     * Finds users older than a specified date.
     *
     * @param date the date to compare against.
     * @return a list of users who were born before the specified date.
     */
    @Override
    public List<User> findUsersOlderThan(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }
}
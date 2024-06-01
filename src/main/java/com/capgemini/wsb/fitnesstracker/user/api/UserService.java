package com.capgemini.wsb.fitnesstracker.user.api;


import java.time.LocalDate;
import java.util.List;
/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
 
 public interface UserService {
     List<User> getAllUsers();
     User getUserById(Long id) throws UserNotFoundException;
     User createUser(User user);
     User updateUser(Long id, User user) throws UserNotFoundException;
     void deleteUser(Long id) throws UserNotFoundException;
     List<User> searchUsersByEmail(String email);
     List<User> searchUsersByAgeGreaterThan(int age);
     List<User> findUsersOlderThan(LocalDate date);
 }
 
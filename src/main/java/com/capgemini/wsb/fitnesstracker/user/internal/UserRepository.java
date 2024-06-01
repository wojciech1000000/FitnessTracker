package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Query searching users by email address (case insensitive) containing a fragment of the email.
     *
     * @param emailFragment fragment of the email of the users to search
     * @return List of users matching the search criteria
     */
    default List<User> findByEmailContainingIgnoreCase(String emailFragment) {
        return findAll().stream()
                        .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                        .toList();
    }

    /**
     * Query searching users by age greater than a given age.
     *
     * @param age age to compare with user's age
     * @return List of users older than the given age
     */
    default List<User> findByAgeGreaterThan(int age) {
        LocalDate cutoffDate = LocalDate.now().minusYears(age);
        return findAll().stream()
                        .filter(user -> user.getBirthdate().isBefore(cutoffDate))
                        .collect(Collectors.toList());
    }

    /**
     * Finds users who were born before the specified date.
     *
     * @param date the date to compare against.
     * @return a list of users who were born before the specified date.
     */
    List<User> findByBirthdateBefore(LocalDate date);
}

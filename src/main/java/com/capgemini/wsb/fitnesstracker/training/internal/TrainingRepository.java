package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository for managing Training entities.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds trainings by the user ID.
     *
     * @param userId the ID of the user.
     * @return a list of trainings associated with the specified user ID.
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds trainings by activity type.
     *
     * @param activityType the type of activity.
     * @return a list of trainings with the specified activity type.
     */
    List<Training> findByActivityType(ActivityType activityType);

    /**
     * Finds trainings that have ended after the specified date.
     *
     * @param afterTime the date to compare with the end time of the trainings.
     * @return a list of trainings that have ended after the specified date.
     */
    List<Training> findByEndTimeAfter(Date afterTime);
}

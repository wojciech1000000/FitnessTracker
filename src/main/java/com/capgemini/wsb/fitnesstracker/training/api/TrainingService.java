package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Date;
import java.util.List;

/**
 * Service interface for managing trainings.
 */
public interface TrainingService {

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings.
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves trainings for a specific user.
     *
     * @param userId the ID of the user.
     * @return a list of trainings for the specified user.
     */
    List<Training> getTrainingsByUser(Long userId);

    /**
     * Retrieves trainings by activity type.
     *
     * @param activityType the type of activity.
     * @return a list of trainings with the specified activity type.
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves all finished trainings after a specified time.
     *
     * @param afterTime the date to filter finished trainings by.
     * @return a list of finished trainings after the specified date.
     */
    List<Training> getFinishedTrainingsAfter(Date afterTime);

    /**
     * Creates a new training.
     *
     * @param training the training to create.
     * @return the created training.
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training.
     *
     * @param trainingId the ID of the training to update.
     * @param training the training data to update.
     * @return the updated training.
     */
    Training updateTraining(Long trainingId, Training training);
}

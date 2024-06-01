package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing trainings.
 */
@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings.
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves trainings for a specific user.
     *
     * @param userId the ID of the user.
     * @return a list of trainings for the specified user.
     */
    @Override
    public List<Training> getTrainingsByUser(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves trainings by activity type.
     *
     * @param activityType the activity type to filter by.
     * @return a list of trainings with the specified activity type.
     */
    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Retrieves all finished trainings after a specified time.
     *
     * @param afterTime the time to filter finished trainings by.
     * @return a list of finished trainings after the specified time.
     */
    @Override
    public List<Training> getFinishedTrainingsAfter(Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    /**
     * Creates a new training.
     *
     * @param training the training to create.
     * @return the created training.
     * @throws RuntimeException if the user associated with the training is not found.
     */
    @Override
    public Training createTraining(Training training) {
        Optional<User> user = userRepository.findById(training.getUser().getId());
        if (user.isPresent()) {
            training.setUser(user.get());
            return trainingRepository.save(training);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Updates an existing training.
     *
     * @param trainingId the ID of the training to update.
     * @param updatedTraining the updated training data.
     * @return the updated training.
     * @throws RuntimeException if the training with the specified ID is not found.
     */
    @Override
    public Training updateTraining(Long trainingId, Training updatedTraining) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));
        training.setStartTime(updatedTraining.getStartTime());
        training.setEndTime(updatedTraining.getEndTime());
        training.setActivityType(updatedTraining.getActivityType());
        training.setDistance(updatedTraining.getDistance());
        training.setAverageSpeed(updatedTraining.getAverageSpeed());
        return trainingRepository.save(training);
    }
}

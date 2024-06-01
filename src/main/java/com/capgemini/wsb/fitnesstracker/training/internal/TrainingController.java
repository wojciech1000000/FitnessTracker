package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * REST controller for managing trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    /**
     * Retrieves all trainings.
     *
     * @return a ResponseEntity containing a list of all trainings.
     */
    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        List<Training> trainings = trainingService.getAllTrainings();
        return ResponseEntity.ok(trainings);
    }

    /**
     * Retrieves trainings for a specific user.
     *
     * @param userId the ID of the user.
     * @return a ResponseEntity containing a list of trainings for the specified user.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Training>> getTrainingsByUser(@PathVariable Long userId) {
        List<Training> trainings = trainingService.getTrainingsByUser(userId);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Retrieves all finished trainings after a specified time.
     *
     * @param afterTime the date to filter finished trainings by.
     * @return a ResponseEntity containing a list of finished trainings after the specified date.
     */
    @GetMapping("/finished/{afterTime}")
    public ResponseEntity<List<Training>> getFinishedTrainingsAfterTime(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        List<Training> trainings = trainingService.getFinishedTrainingsAfter(afterTime);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Retrieves trainings by activity type.
     *
     * @param activityType the type of activity to filter by.
     * @return a ResponseEntity containing a list of trainings with the specified activity type.
     */
    @GetMapping("/activityType")
    public ResponseEntity<List<Training>> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        List<Training> trainings = trainingService.getTrainingsByActivityType(activityType);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Creates a new training.
     *
     * @param training the training to create.
     * @return a ResponseEntity containing the created training.
     */
    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        Training createdTraining = trainingService.createTraining(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTraining);
    }

    /**
     * Updates an existing training.
     *
     * @param trainingId the ID of the training to update.
     * @param training the training data to update.
     * @return a ResponseEntity containing the updated training.
     */
    @PutMapping("/{trainingId}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long trainingId, @RequestBody Training training) {
        Training updatedTraining = trainingService.updateTraining(trainingId, training);
        return ResponseEntity.ok(updatedTraining);
    }
}

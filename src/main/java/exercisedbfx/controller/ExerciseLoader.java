package exercisedbfx.controller;

import exercisedbfx.model.Exercise;

/**
 * Interface for loading exercise information in controllers.
 */
public interface ExerciseLoader {
    /**
     * Loads exercise information based on the provided exercise.
     *
     * @param current the exercise to load.
     */
    void loadExercise(Exercise current);
}

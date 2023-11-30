package exercisedbfx.controller;

import exercisedbfx.model.Exercise;

public interface ExerciseLoader {
    /**
     * Loads exercise information based on the provided exercise.
     *
     * @param current the exercise to load.
     */
    void loadExercise(Exercise current);
}

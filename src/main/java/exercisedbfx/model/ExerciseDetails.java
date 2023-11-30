package exercisedbfx.model;

import java.util.List;

/**
 * Class represents a full description of the Exercise.
 */
public class ExerciseDetails {
    private String id; // an exercise id
    private String name; // an exercise name
    private String bodyPart; // a body part to be trained by this exercise
    private String target; // a target muscle to be trained
    private String equipment; // the equipment required for the exercise
    private String gifUrl; // the URL of the GIF for the exercise
    private List<String> secondaryMuscles; // the list of secondary muscles involved in the exercise
    private List<String> instructions; //  the list of instructions for the exercise

    public ExerciseDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public List<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(List<String> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}

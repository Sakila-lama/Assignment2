package exercisedbfx.model;

import java.util.Objects;

// Class represents a short description of the Exercise
public class ShortExerciseDescription {
    private String id;
    private String name;
    private String bodyPart;
    private String target;

    public ShortExerciseDescription() {
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

    @Override
    public String toString() {
        return "Exercise Name: " + name + ", Target: " + target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortExerciseDescription that = (ShortExerciseDescription) o;

        return Objects.equals(name, that.name);
    }
}

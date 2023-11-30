package exercisedbfx.model;

import java.util.Objects;

/**
 * Class represents a short description of the Exercise.
 */
public class Exercise {
    private String id; // an exercise id
    private String name; // an exercise name
    private String bodyPart; // a body part to be trained by this exercise
    private String target; // a target muscle to be trained

    public Exercise() {
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

    /**
     * Returns a string representation of the Exercise.
     *
     * @return a string representation of the Exercise.
     */
    @Override
    public String toString() {
        return "Exercise Name: " + name + ", Target: " + target;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Implicitly uses in ListView to select the current Exercise. Compares by the `name` field.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise that = (Exercise) o;

        return Objects.equals(name, that.name);
    }
}

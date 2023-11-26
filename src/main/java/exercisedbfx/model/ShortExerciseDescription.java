package exercisedbfx.model;

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
        return "Body part: " + bodyPart + ", Name: " + name + ", Target: " + target;
    }
}

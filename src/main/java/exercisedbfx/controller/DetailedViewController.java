package exercisedbfx.controller;

import exercisedbfx.Main;
import exercisedbfx.model.Exercise;
import exercisedbfx.model.ExerciseDetails;
import exercisedbfx.service.APIUtility;
import exercisedbfx.service.SceneChanger;
import java.util.Objects;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Controller class for the DetailedView.fxml, responsible for displaying detailed information
 * about a selected exercise.
 */
public class DetailedViewController implements ExerciseLoader {
    // stores the current selected item from the GeneralView
    private Exercise current;
    @FXML
    private Text txtBodyPart;
    @FXML
    private Text txtEquipment;
    @FXML
    private Text txtTarget;
    @FXML
    private Text txtSecondary;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnBack;
    @FXML
    private Text txtName;
    @FXML
    private TextArea txtInstructions;

    /**
     * Switches to the GeneralView scene.
     *
     * @param event the event triggering the scene change.
     */
    @FXML
    public void switchToGeneral(Event event) {
        SceneChanger.changeScenes(event, SceneChanger.GENERAL_VIEW, "Exercise Explorer", current);
    }

    /**
     * Loads details of the selected exercise into the DetailedView.
     *
     * @param current the selected exercise in GeneralView.
     */
    @Override
    public void loadExercise(Exercise current) {
        this.current = current;
        ExerciseDetails exercise = APIUtility.getExerciseDetailsById(current.getId());

        txtName.setText(exercise.getName());
        txtBodyPart.setText(exercise.getBodyPart());
        txtEquipment.setText(exercise.getEquipment());
        txtTarget.setText(exercise.getTarget());
        txtSecondary.setText(String.join("\n", exercise.getSecondaryMuscles()));
        txtInstructions.setText(String.join("\n", exercise.getInstructions()));
        // Loads an image form the gifUrl
        try {
            imgView.setImage(new Image(exercise.getGifUrl()));
        } catch (IllegalArgumentException e) {
            imgView.setImage(new Image(Objects.requireNonNull(
                    Main.class.getResourceAsStream("/exercisedbfx/images/default.jpg"))));
        }
        imgView.setPreserveRatio(true);
    }
}

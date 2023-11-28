package exercisedbfx.controller;

import exercisedbfx.service.APIUtility;
import exercisedbfx.model.FullExerciseDescription;
import exercisedbfx.service.SceneChanger;
import exercisedbfx.model.ShortExerciseDescription;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DetailedViewController implements ExerciseLoader {
    // stores the current selected item from the GeneralView
    private ShortExerciseDescription current;
    @FXML
    private Text txtBodyPart;
    @FXML
    private Text txtEquipment;
    @FXML
    private Text txtTarget;
    @FXML
    private Text txtSecondary;
    @FXML
    private Text txtInstructions;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnBack;
    @FXML
    private Text txtName;

    @FXML
    public void switchToGeneral(Event event) {
        SceneChanger.changeScenes(event, SceneChanger.GENERAL_VIEW, "Exercise Explorer", current);
    }

    @Override
    public void loadExercise(ShortExerciseDescription current) {
        this.current = current;
        FullExerciseDescription exercise = APIUtility.getFullDescriptionById(current.getId());

        txtName.setText(exercise.getName());
        txtBodyPart.setText(exercise.getBodyPart());
        txtEquipment.setText(exercise.getEquipment());
        txtTarget.setText(exercise.getTarget());
        txtSecondary.setText(String.join("\n", exercise.getSecondaryMuscles()));
        txtInstructions.setText(String.join("\n", exercise.getInstructions()));
        // Loads an image form the gifUrl
        imgView.setImage(new Image(exercise.getGifUrl()));
        imgView.setPreserveRatio(true);
    }
}

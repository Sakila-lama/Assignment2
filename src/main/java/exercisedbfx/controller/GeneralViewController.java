package exercisedbfx.controller;

import exercisedbfx.model.Exercise;
import exercisedbfx.service.APIUtility;
import exercisedbfx.service.SceneChanger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller class for the GeneralView.fxml, responsible for displaying a list of exercises
 * based on selected body parts and navigating to the DetailedView for more information.
 */
public class GeneralViewController implements Initializable, ExerciseLoader {
    private Exercise current;
    @FXML
    private Button btnGetInfo;
    @FXML
    private ListView<Exercise> listView;
    @FXML
    private ComboBox<String> cmbBoxBodyParts;
    @FXML
    private Label statusLabel;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param url            The location used to resolve relative paths for the root object,
     *                       or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object,
     *                       or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializes a comboBox. Get body parts from the API
        List<String> bodyParts = APIUtility.getBodyParts();
        ObservableList<String> list = FXCollections.observableList(bodyParts);
        cmbBoxBodyParts.setItems(list);
    }

    /**
     * Retrieves the information about exercises by selected body part.
     *
     * @param actionEvent the event triggering the action.
     */
    @FXML
    public void getExerciseList(ActionEvent actionEvent) {
        // Retrieves the information about exercises by selected body part
        String bodyPart = cmbBoxBodyParts.getSelectionModel().getSelectedItem();
        loadExerciseByBodyPart(bodyPart);
    }

    /**
     * Switches to the DetailedView scene.
     *
     * @param event the event triggering the scene change.
     */
    @FXML
    public void switchToDetails(Event event) {
        // Sets the current exercise
        current = listView.getSelectionModel().getSelectedItem();
        // Handling a case if a ListView is empty
        if (current == null) {
            return;
        }
        SceneChanger.changeScenes(event, SceneChanger.DETAILED_VIEW,
                "Detailed Information", current);
    }

    /**
     * Overridden method to load an exercise.
     *
     * @param current the exercise to load.
     */
    @Override
    public void loadExercise(Exercise current) {
        // Sets the current exercise
        this.current = current;
        // Loads exercises from the API
        loadExerciseByBodyPart(current.getBodyPart());
        // Selects the value in a ComboBox
        cmbBoxBodyParts.getSelectionModel().select(current.getBodyPart());
        // Selects the current exercise and scroll down to this exercise
        listView.getSelectionModel().select(current);
        listView.scrollTo(current);
    }

    /**
     * Loads exercises from the API based on the selected body part.
     * Displays tre result in the ListView. Updates the status.
     *
     * @param bodyPart the selected body part.
     */
    private void loadExerciseByBodyPart(String bodyPart) {
        // Loads exercises from the API
        List<Exercise> exercises = APIUtility.getExercisesByBodyPart(bodyPart);
        ObservableList<Exercise> list =
                FXCollections.observableList(exercises);
        listView.setItems(list);
        listView.scrollTo(0); // scroll to the top
        statusLabel.setText("Exercises found: " + list.size());
    }
}

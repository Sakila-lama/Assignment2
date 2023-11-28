package exercisedbfx.controller;

import exercisedbfx.service.APIUtility;
import exercisedbfx.service.SceneChanger;
import exercisedbfx.model.ShortExerciseDescription;
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

public class GeneralViewController implements Initializable, ExerciseLoader {
    private ShortExerciseDescription current;
    @FXML
    private Button btnGetInfo;
    @FXML
    private ListView<ShortExerciseDescription> listView;
    @FXML
    private ComboBox<String> cmbBoxBodyParts;
    @FXML
    private Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializes a comboBox. Get body parts from the API
        List<String> bodyParts = APIUtility.getBodyParts();
        ObservableList<String> list = FXCollections.observableList(bodyParts);
        cmbBoxBodyParts.setItems(list);
    }

    @FXML
    public void getExerciseList(ActionEvent actionEvent) {
        // Retrieves the information about exercises by selected body part
        String bodyPart = cmbBoxBodyParts.getSelectionModel().getSelectedItem();
        loadExerciseByBodyPart(bodyPart);

    }

    @FXML
    public void switchToDetails(Event event) {
        current = listView.getSelectionModel().getSelectedItem();
        SceneChanger.changeScenes(event, SceneChanger.DETAILED_VIEW, "Detailed Information", current);
    }

    @Override
    public void loadExercise(ShortExerciseDescription current) {
        this.current = current;
        loadExerciseByBodyPart(current.getBodyPart());
        cmbBoxBodyParts.getSelectionModel().select(current.getBodyPart());
        listView.getSelectionModel().select(current);
    }

    private void loadExerciseByBodyPart(String bodyPart) {
        List<ShortExerciseDescription> exercises = APIUtility.getExercisesByBodyPart(bodyPart);
        ObservableList<ShortExerciseDescription> list =
                FXCollections.observableList(exercises);
        listView.setItems(list);
        statusLabel.setText("Exercises found: " + list.size());
    }
}

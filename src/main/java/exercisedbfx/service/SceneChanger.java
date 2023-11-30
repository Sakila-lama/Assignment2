package exercisedbfx.service;

import exercisedbfx.controller.ExerciseLoader;
import exercisedbfx.model.Exercise;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneChanger {
    // The FXML resource path for the general view
    public static final String GENERAL_VIEW = "/exercisedbfx/controller/GeneralView.fxml";
    // The FXML resource path for the detailed view
    public static final String DETAILED_VIEW = "/exercisedbfx/controller/DetailedView.fxml";

    /**
     * Changes the scene in the specified stage with the given FXML resource and title.
     *
     * @param stage        the stage to change the scene in.
     * @param fxmlResource the FXML resource path.
     * @param title        the title of the new scene.
     */
    public static void changeScenes(Stage stage, String fxmlResource, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneChanger.class.getResource(fxmlResource));
            Scene scene = new Scene(fxmlLoader.load());

            loadIcon(stage);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Error loading the resource " + fxmlResource, e);
        }
    }

    /**
     * Changes the scene in the stage associated with the event with the given FXML resource,
     * title, and current Exercise.
     *
     * @param event        the event triggering the scene change.
     * @param fxmlResource the FXML resource path.
     * @param title        the title of the new scene.
     * @param current      the current Exercise.
     */
    public static void changeScenes(Event event, String fxmlResource, String title,
                                    Exercise current) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneChanger.class.getResource(fxmlResource));
            Scene scene = new Scene(fxmlLoader.load());
            // Gets a controller from the FXMLLoader
            ExerciseLoader controller = fxmlLoader.getController();
            // loads appropriate information according to current exercise
            // for specific controller
            controller.loadExercise(current);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loadIcon(stage);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Error loading the resource " + fxmlResource, e);
        }
    }

    /**
     * Loads the application icon into the specified stage.
     *
     * @param stage the stage to load the icon into.
     */
    private static void loadIcon(Stage stage) {
        InputStream iconStream = SceneChanger.class.getResourceAsStream(
                "/exercisedbfx/images/icon.png");
        if (iconStream != null) {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        }
    }
}

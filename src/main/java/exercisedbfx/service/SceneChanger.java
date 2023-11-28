package exercisedbfx.service;

import exercisedbfx.controller.ExerciseLoader;
import exercisedbfx.model.ShortExerciseDescription;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneChanger {
    public static final String GENERAL_VIEW = "/exercisedbfx/controller/GeneralView.fxml";
    public static final String DETAILED_VIEW = "/exercisedbfx/controller/DetailedView.fxml";

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

    public static void changeScenes(Event event, String fxmlResource, String title,
                                    ShortExerciseDescription current) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneChanger.class.getResource(fxmlResource));
            Scene scene = new Scene(fxmlLoader.load());

            ExerciseLoader controller = fxmlLoader.getController();
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

    private static void loadIcon(Stage stage) {
        InputStream iconStream = SceneChanger.class.getResourceAsStream(
                "/exercisedbfx/images/icon.png");
        if (iconStream != null) {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        }
    }
}

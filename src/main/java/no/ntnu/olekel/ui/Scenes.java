package no.ntnu.olekel.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Scenes {
    private URL url;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method that loads a page given by the url of the application.
     *
     * @param event         Event.
     * @param url           Url to the fxml of the scene to be loaded.
     * @throws IOException  Exception.
     */
    public void loadScene(ActionEvent event, URL url) throws  IOException {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(url));
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setMinHeight(650);
            stage.setMinWidth(820);
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("whattafac");
        }
    }

}

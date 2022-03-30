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

public class LoadScenes {
    private URL url;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method that loads the main page of the application.
     *
     * @param event         Event.
     * @throws IOException  Exception
     */
    public void loadMainPage(ActionEvent event) throws IOException {
        url = getClass().getClassLoader().getResource("fxml-files/mainPage.fxml");
        root = FXMLLoader.load(Objects.requireNonNull(url));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

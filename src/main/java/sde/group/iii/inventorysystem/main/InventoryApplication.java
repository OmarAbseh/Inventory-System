package sde.group.iii.inventorysystem.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
//        https://www.canva.com/design/DAGY2kJQaLo/lruZbx3fB1tEtBMT4CCmfA/edit
        try {
            // Load the initial login page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sde/group/iii/inventorysystem/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Inventory Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

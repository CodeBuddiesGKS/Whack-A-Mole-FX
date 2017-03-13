package whackamole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("field-layout.fxml"));
        primaryStage.setTitle("Whack-A-Mole! v1.0");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setOnCloseRequest( event -> System.exit(0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

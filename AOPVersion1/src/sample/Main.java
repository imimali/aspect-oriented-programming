package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.Repository;

import java.util.logging.Logger;

public class Main extends Application {
    //private static final Logger log = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        //log.info("Starting application");
        Repository repository = new Repository();
        repository.getAll();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        MainController mainController = loader.getController();
        primaryStage.setScene(new Scene(root, 500, 375));
        for (int i = 0; i < 2; i++) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = loader1.load();
            VoterController voterController = loader1.getController();
            mainController.registerObserver(voterController);
            loader1.setController(voterController);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 400, 400));
            stage.show();
        }
        primaryStage.show();
        //log.info("Windows launched");

    }


    public static void main(String[] args) {
        launch(args);
    }
}

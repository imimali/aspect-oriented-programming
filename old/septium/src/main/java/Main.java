import com.migaop.controller.PersonFxController;
import com.migaop.repository.RepositoryImpl;
import com.migaop.service.PersonService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        PersonService personService = new PersonService(new RepositoryImpl());
        for (int i = 0; i < 3; i++) {

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/window.fxml"));
            Parent root1 = loader.load();
            PersonFxController controller = loader.getController();
            controller.setPersonService(personService);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 500, 600));
            stage.show();
        }
    }
}

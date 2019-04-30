package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import persistence.Candidate;
import persistence.Repository;
import persistence.RepositoryJdbcImpl;

public class Main extends Application {
    //private static final Logger log = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        //log.info("Starting application");
        final ApplicationContext context = new ClassPathXmlApplicationContext(
                "SpringAspect*.xml"
        );
        //Repository repository = (Repository) context.getBean("basicRepo");
        Repository repository1=(Repository)context.getBean("jdbcRepo");
        System.out.println(repository1.getAll());
        //Repository repository = new RepositoryJdbcImpl();
        //repository.getAll();
        Candidate candidate = new Candidate("Marcus Aurelius", 22);
        candidate.setId(1L);
        //repository.update(candidate);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/admin.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");

        MainController mainController = loader.getController();
        primaryStage.setScene(new Scene(root, 500, 375));
        for (int i = 0; i < 2; i++) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/sample.fxml"));
            Parent root1 = loader1.load();
            VoterController voterController = loader1.getController();
            voterController.setRepository(repository1);
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

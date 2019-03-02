package sample;

import custom.Subject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import persistence.Candidate;
import persistence.Repository;

import java.util.List;
import java.util.logging.Logger;

public class MainController extends Subject {
    private static final Logger log = Logger.getLogger(MainController.class.getName());
    @FXML
    private ListView<Candidate> candidatesList;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField votesTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private Repository repository;
    private Candidate selectedCandidate = null;

    @FXML
    public void initialize() {
        log.info("Initializing");
        populateCandidatesList();
        candidatesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectCandidate());
        log.info("Initialization done");
    }

    public MainController() {
        this.repository = new Repository();
    }

    private void selectCandidate() {
        log.info("Selecting candidate");
        Candidate candidate = candidatesList.getSelectionModel().getSelectedItem();
        if (candidate != null) {
            selectedCandidate = candidate;
            nameTextField.setText(candidate.getName());
            votesTextField.setText(String.valueOf(candidate.getVotes()));
        }
        log.info("Candidate selected is: " + selectedCandidate);
    }

    @Override
    public void notifyObservers() {
        log.info("Notifying observers");
        super.notifyObservers();
        populateCandidatesList();
        log.info("Notifying observers done");
    }

    private void populateCandidatesList() {
        log.info("Populating list");
        List<Candidate> candidates = repository.getAll();
        candidatesList.getItems().setAll(candidates);
        log.info("Populate done");
    }

    @FXML
    private void addCandidate() {
        log.info("Adding candidate");
        String name = nameTextField.getText();
        Candidate candidate = new Candidate(name, 0);
        repository.store(candidate);
        populateCandidatesList();
        notifyObservers();
        log.info("Added candidate " + candidate);
    }

    @FXML
    private void updateCandidate() {
        log.info("Updating candidate");
        if (this.selectedCandidate != null)
            repository.update(this.selectedCandidate);
        populateCandidatesList();
        notifyObservers();
        log.info("Update candidate done");
    }

    @FXML
    private void deleteCandidate() {
        log.info("Dleeting candidate");
        if (this.selectedCandidate != null) {
            repository.delete(selectedCandidate.getId());
            populateCandidatesList();
            notifyObservers();
        }
        log.info("Deleted candidate");
    }
}

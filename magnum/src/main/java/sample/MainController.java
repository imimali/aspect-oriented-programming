package sample;

import custom.Subject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import persistence.Candidate;
import persistence.Repository;
import persistence.RepositoryImpl;

import java.util.List;

public class MainController extends Subject {
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
        populateCandidatesList();
        candidatesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectCandidate());
    }

    public MainController() {
        this.repository = new RepositoryImpl();
    }

    private void selectCandidate() {
        Candidate candidate = candidatesList.getSelectionModel().getSelectedItem();
        if (candidate != null) {
            selectedCandidate = candidate;
            nameTextField.setText(candidate.getName());
            votesTextField.setText(String.valueOf(candidate.getVotes()));
        }
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
        populateCandidatesList();
    }

    private void populateCandidatesList() {
        List<Candidate> candidates = repository.getAll();
        candidatesList.getItems().setAll(candidates);
    }

    @FXML
    private void addCandidate() {
        String name = nameTextField.getText();
        Candidate candidate = new Candidate(name, 0);
        repository.store(candidate);
        populateCandidatesList();
        notifyObservers();
    }

    @FXML
    private void updateCandidate() {
        if (this.selectedCandidate != null) {
            Candidate candidate = new Candidate(selectedCandidate.getId(), nameTextField.getText(), Integer.valueOf(votesTextField.getText()));
            repository.update(candidate);
        }
        populateCandidatesList();
        notifyObservers();
    }

    @FXML
    private void deleteCandidate() {
        if (this.selectedCandidate != null) {
            repository.delete(selectedCandidate.getId());
            populateCandidatesList();
            notifyObservers();
        }
    }
}

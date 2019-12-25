package sample;

import custom.MyObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import persistence.Candidate;
import persistence.Repository;
import persistence.RepositoryImpl;

import java.util.List;

public class VoterController extends MyObserver {
    @FXML
    private Label nameLabel;

    @FXML
    private ListView<Candidate> candidatesListView;

    @FXML
    private Button voteButton;

    private Repository repository;
    private Candidate selectedCandidate = null;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public VoterController() {
        repository = new RepositoryImpl();

    }

    public void initialize() {
        populateCandidatesList();
        candidatesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectCandidate());
    }

    @Override
    public void update() {
        populateCandidatesList();
    }

    private void populateCandidatesList() {
        List<Candidate> candidates = repository.getAll();
        candidatesListView.getItems().setAll(candidates);
    }

    private void selectCandidate() {
        Candidate candidate = candidatesListView.getSelectionModel().getSelectedItem();
        if (candidate != null) {
            selectedCandidate = candidate;
        }
    }

    @FXML
    private void voteCandidate() {
        if (selectedCandidate != null) {
            Candidate candidate = new Candidate(selectedCandidate.getId(), selectedCandidate.getName(), selectedCandidate.getVotes() + 1);
            repository.update(candidate);
            populateCandidatesList();
            subject.notifyObservers();
        }
    }

}

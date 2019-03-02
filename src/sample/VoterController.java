package sample;

import custom.MyObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import persistence.Candidate;
import persistence.Repository;

import java.util.List;
import java.util.logging.Logger;

public class VoterController extends MyObserver {
    private final static Logger log = Logger.getLogger(VoterController.class.getName());
    @FXML
    private Label nameLabel;

    @FXML
    private ListView<Candidate> candidatesListView;

    @FXML
    private Button voteButton;

    private Repository repository;
    private Candidate selectedCandidate = null;

    public VoterController() {
        repository = new Repository();

    }

    public void initialize() {
        log.info("Initializing");
        populateCandidatesList();
        candidatesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectCandidate());
        log.info("Initialization done");
    }

    @Override
    public void update() {
        log.info("Updating");
        populateCandidatesList();
        log.info("Update done");
    }

    private void populateCandidatesList() {
        log.info("Populating list");
        List<Candidate> candidates = repository.getAll();
        candidatesListView.getItems().setAll(candidates);
        log.info("Populated list with " + candidates);
    }

    private void selectCandidate() {
        log.info("Selecting candidate");
        Candidate candidate = candidatesListView.getSelectionModel().getSelectedItem();
        if (candidate != null) {
            selectedCandidate = candidate;
        }
        log.info("Selected candidate: " + candidate);
    }

    @FXML
    private void voteCandidate() {
        log.info("Voting for candidate");
        if (selectedCandidate != null) {
            Candidate candidate = new Candidate(selectedCandidate.getId(), selectedCandidate.getName(), selectedCandidate.getVotes() + 1);
            repository.update(candidate);
            subject.notifyObservers();
        }
        log.info("Voted for candidate");
    }

}

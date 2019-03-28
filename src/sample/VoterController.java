package sample;

import asp.SubjectChangerMethod;
import custom.MyObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import persistence.Candidate;
import persistence.Repository;

import java.util.List;

public class VoterController/* extends MyObserver*/ {
    @FXML
    private Label nameLabel;

    @FXML
    private ListView<Candidate> candidatesListView;

    @FXML
    private Button voteButton;

    @FXML
    private Button downButton;

    private Repository repository;
    private Candidate selectedCandidate = null;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public VoterController() {
        repository = new Repository();

    }

    public void sayHelloToMain(MainController mainController) {
        System.out.println("saying hello to main controller: " + mainController);
    }

    public void initialize() {
        populateCandidatesList();
        candidatesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectCandidate());
    }

   /* @Override
    public void update() {
        populateCandidatesList();
    }*/

    public void populateCandidatesList() {
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
    @SubjectChangerMethod
    private void upVoteCandidate() {
        if (selectedCandidate != null) {
            Candidate candidate = new Candidate(selectedCandidate.getId(),
                    selectedCandidate.getName(),
                    selectedCandidate.getVotes() + 1);
            repository.update(candidate);
            // subject.notifyObservers();
        }
    }

    @FXML
    @SubjectChangerMethod
    private void downVoteCandidate() {
        if (selectedCandidate != null) {
            Candidate candidate = new Candidate(selectedCandidate.getId(),
                    selectedCandidate.getName(),
                    selectedCandidate.getVotes() - 1);
            repository.update(candidate);
            // subject.notifyObservers();
        }
    }

}

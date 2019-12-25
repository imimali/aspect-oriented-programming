package com.migaop.controller;

import com.migaop.custom.EnablePerformanceMonitoring;
import com.migaop.custom.SubjectChangerMethod;
import com.migaop.domain.Person;
import com.migaop.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class PersonFxController {

    @FXML
    private ListView<Person> personListView;

    @FXML
    private TextField idLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    private TextField statusLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button loadButton;

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    private PersonService personService;

    private Person selectedPerson;

    @FXML
    @EnablePerformanceMonitoring
    public void initialize() {
        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectPerson());

    }


    private void selectPerson() {
        Person person = personListView.getSelectionModel().getSelectedItem();
        if (person != null) {
            selectedPerson = personService.findById(person.getId());
            idLabel.setText(person.getId().toString());
            nameLabel.setText(person.getName());
            emailLabel.setText(person.getEmail());
            statusLabel.setText(person.getStatus());
        }
    }

    @FXML
    public void populatePersonList() {
        List<Person> personList = personService.getAll();
        personListView.getItems().setAll(personList);
    }

    private Person getPersonFromLabels() {
        Long id = Long.parseLong(idLabel.getText());
        String name = nameLabel.getText();
        String email = emailLabel.getText();
        String status = statusLabel.getText();
        return new Person(id, name, email, status);
    }

    @FXML
    public void addPerson() {
        personService.add(getPersonFromLabels());
        populatePersonList();
    }

    @FXML
    @SubjectChangerMethod
    public void updatePerson() {
        if (selectedPerson != null) {
            personService.update(getPersonFromLabels());
            populatePersonList();
        }
    }

    @FXML
    public void deletePerson() {
        if (selectedPerson != null) {
            personService.delete(selectedPerson.getId());
            populatePersonList();
        }
    }


}


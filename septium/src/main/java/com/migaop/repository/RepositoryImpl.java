package com.migaop.repository;

import com.migaop.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl implements Repository {
    private static final String URL = "jdbc:postgresql://localhost:5432/aop";
    private static final String user = "postgres";
    private static final String password = "postgres";

    @Override
    public List<Person> getAll() {
        String sqlStatement = "SELECT * FROM persons";
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String status = rs.getString("status");
                persons.add(new Person(id, name, email, status));
            }
            return persons;

        } catch (Exception sqle) {
            sqle.printStackTrace();
            return persons;
        }
    }

    @Override
    public Optional<Person> findById(Long id) {
        String sqlStatement = "SELECT * FROM persons where id = ?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String status = rs.getString("status");
                return Optional.of(new Person(id, name, email, status));
            }
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Person person) {
        if (person == null)
            throw new IllegalArgumentException("element must not be null");

        String sql = "INSERT INTO persons(name,email,status) VALUES(?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getEmail());
            ps.setString(3, person.getStatus());

            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        String sql = "UPDATE persons SET name = ?, email = ?, status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getEmail());
            ps.setString(3, person.getStatus());
            ps.setLong(4, person.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE from persons where id=?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}

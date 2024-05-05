package com.example.controller;

import com.example.controller.Habit;
import com.example.controller.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {
    private Connection connection;

    public HabitDAO() {
        connection = DBUtils.getConnection();
    }

    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM habits");
            while (resultSet.next()) {
                Habit habit = new Habit();
                habit.setId(resultSet.getInt("id"));
                habit.setName(resultSet.getString("name"));
                habit.setDescription(resultSet.getString("description"));
                habit.setStartDate(resultSet.getDate("start_date"));
                habit.setStreak(resultSet.getInt("streak"));
                habits.add(habit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habits;
    }

    public void createHabit(Habit habit) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO habits (name, description, start_date, streak) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, habit.getName());
            statement.setString(2, habit.getDescription());
            statement.setDate(3, new java.sql.Date(habit.getStartDate().getTime()));
            statement.setInt(4, habit.getStreak());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                habit.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Habit getHabitById(int id) {
        Habit habit = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM habits WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                habit = new Habit();
                habit.setId(resultSet.getInt("id"));
                habit.setName(resultSet.getString("name"));
                habit.setDescription(resultSet.getString("description"));
                habit.setStartDate(resultSet.getDate("start_date"));
                habit.setStreak(resultSet.getInt("streak"));
            }
        } catch (SQLException e) {
e.printStackTrace();
}
return habit;
}
public void updateHabit(Habit habit) {
    try {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE habits SET name = ?, description = ? WHERE id = ?"
        );
        statement.setString(1, habit.getName());
        statement.setString(2, habit.getDescription());
        statement.setInt(3, habit.getId());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteHabit(int id) {
    try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM habits WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
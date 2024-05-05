package com.example.controller;

import com.example.controller.HabitDAO;
import com.example.controller.Habit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/habits")
public class HabitController extends HttpServlet {
    private HabitDAO habitDAO;

    @Override
    public void init() {
        habitDAO = new HabitDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listHabits(request, response);
        } else if (action.equals("create")) {
            showCreateForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteHabit(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")) {
            createHabit(request, response);
        } else if (action.equals("update")) {
            updateHabit(request, response);
        }
    }

    private void listHabits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Habit> habits = habitDAO.getAllHabits();
        request.setAttribute("habits", habits);
        request.getRequestDispatcher("/WEB-INF/views/habits.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/habitForm.jsp").forward(request, response);
    }

    private void createHabit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Habit habit = new Habit(name, description);
        habitDAO.createHabit(habit);
        response.sendRedirect(request.getContextPath() + "/habits");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Habit habit = habitDAO.getHabitById(id);
        request.setAttribute("habit", habit);
        request.getRequestDispatcher("/WEB-INF/views/habitForm.jsp").forward(request, response);
    }

    private void updateHabit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Habit habit = new Habit(id, name, description);
        habitDAO.updateHabit(habit);
        response.sendRedirect(request.getContextPath() + "/habits");
    }

    private void deleteHabit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        habitDAO.deleteHabit(id);
        response.sendRedirect(request.getContextPath() + "/habits");
    }
}
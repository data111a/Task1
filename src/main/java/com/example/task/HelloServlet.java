package com.example.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/show-cities")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, jakarta.servlet.ServletException {
        System.out.println("made get method");
        resp.setContentType("text/html");
        req.setAttribute("FirstName" , req.getParameter("Name_1"));
        getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("made post method");
        req.setAttribute("country" , req.getParameter("country"));
        Connection connection = null;
        Statement statement = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "Just4Freedom.";
        String country = req.getParameter("country");
        List<String> cities = new ArrayList<>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connected to mySQL database");
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select *from city where countryCode='\" + country + \'");
            while (result.next()){
                String cityName = result.getString("cityName");
                List<String> cityNames = new ArrayList<>();
                cityNames.add(cityName);
                cities.add(String.valueOf(cityNames));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/cities.jsp").forward(req, resp);

//        try {
//            statement = (Statement) connection.createStatement();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
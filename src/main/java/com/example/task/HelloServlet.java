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
        String county = req.getParameter("country");
        List<String> cities = new ArrayList<>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connected to mySQL database");
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select c.Name from city as c\n" +
                    "left join  country as ctr\n" +
                    "on c.countryCode=ctr.code\n" +
                    "where ctr.name=\'" + county + "\'");
            while (result.next()){
                String cityName = result.getString("Name");
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
        req.setAttribute("cities",cities);//ბრჭტყალებში რა სახელსაც დაერკმევა, მძიმის მარჯვნივ ეს შენი ლისტი
        getServletContext().getRequestDispatcher("/cities.jsp").forward(req, resp);

    }
}
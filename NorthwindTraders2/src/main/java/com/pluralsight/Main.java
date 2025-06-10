package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        if (args.length != 2) {
            System.out.println("Set <username> and <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind", username, password);

        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.printf("%-5s %-40s %-10s %-10s\n", "Id", "Name", "Price", "Stock");
        System.out.printf("----------------------------------------------------------------\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("ProductID");
            String name = resultSet.getString("ProductName");
            double price = resultSet.getDouble("UnitPrice");
            int stock = resultSet.getInt("UnitsInStock");

            System.out.printf("%-5d %-40s $%-9.2f %5d\n", id, name, price, stock);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
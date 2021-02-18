package com.michael.database.model;

import com.michael.database.view.ConsoleHelper;

import java.sql.*;

public class DBConnectionManager {
    private  final String URL;
    private  final String USERNAME;
    private  final String PASSWORD;
    private Statement statement;
    private Connection connection;
    private String errorMessage = "";

    public DBConnectionManager(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void establishConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Driver not registered");
            ConsoleHelper.writeMessage(this.getClass().getName() + e.getErrorCode());
        }
        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            if(e.getSQLState().contains("08S01")){
                errorMessage = "Communication error. No SQL server response. Probably wrong port." + e.getSQLState();
            }
            else if (e.getSQLState().contains("42000")){
                errorMessage = "Syntax error or access violation. Incorrect SQL request or wrong database name. " + e.getSQLState();
            }
            else if(e.getSQLState().contains("28000")){
                errorMessage = "Login failed. Incorrect login/password. " + e.getSQLState();
            }
            else {
                errorMessage = "SQL Error. " + e.getSQLState();
            }
            ConsoleHelper.writeMessage(errorMessage);
        }
        //return connection;
    }

    public Statement getStatement(){
        return statement;
    }
    public Statement closeConnection(){
        try {
            statement.close();
            connection.close();
            System.out.println("statement closed: " + statement.isClosed());
            System.out.println("dbConnectionManager closed: " + connection.isClosed());
        } catch (SQLException e) {
            ConsoleHelper.writeMessage(this.getClass().getName() + " " + e.getErrorCode());
        }
        return statement;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

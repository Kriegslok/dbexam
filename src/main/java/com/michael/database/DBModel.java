package com.michael.database;

import com.michael.database.view.ConsoleHelper;

import java.sql.*;

public class DBModel {
    private  String URL;
    private  String USERNAME;
    private  String PASSWORD;
    private Statement statement;
    private String errorMessage;

    public Statement getStatement() {
        return statement;
    }

    public DBModel(String URL, String USERNAME, String PASSWORD) throws SQLException {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.statement = setConnection();
    }

    private Statement setConnection() throws SQLException {
        //Statement statement;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e){
            ConsoleHelper.writeMessage("Driver not registered");
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        }
        catch (SQLException throwables) {
            ConsoleHelper.writeMessage("Exception: " + throwables.getSQLState());
            if(throwables.getSQLState().contains("08S01")){
                ConsoleHelper.writeMessage("Communication error. No SQL server response. Probably wrong port." + throwables.getSQLState());
            }
            else if (throwables.getSQLState().contains("42000")){
                ConsoleHelper.writeMessage("Syntax error or access violation. Incorrect SQL request or wrong database name. " + throwables.getSQLState());
            }
            else if(throwables.getSQLState().contains("28000")){
                ConsoleHelper.writeMessage("Login failed. Incorrect login/password. " + throwables.getSQLState());
            }
            else {
                ConsoleHelper.writeMessage("SQL Error. " + throwables.getSQLState());
            }
        }
//        if(statement == null){
//            throw new NullPointerException("null statement");
//        }

        return statement;
    }

}

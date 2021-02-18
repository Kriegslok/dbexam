package com.michael.database.model;

import com.michael.database.Operation;
import com.michael.database.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Model {
    private  String URL;
    private  String LOGIN;
    private  String PASSWORD;
    private Statement statement;
    private DBConnectionManager dbConnectionManager;
   // private final ArrayList<User> resultList = new ArrayList<>();

    public boolean login(String URL, String login, String password){
        this.URL = URL;
        this.LOGIN = login;
        this.PASSWORD = password;
        getNewStatement(URL, login, password);
        try {
            return (statement != null) && (!statement.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
                if(statement != null){
                dbConnectionManager.closeConnection();
                }
        }
        return false;
    }

    private void getNewStatement(String URL, String login, String password){

        dbConnectionManager = new DBConnectionManager(URL, login, password);
        dbConnectionManager.establishConnection();
        statement = dbConnectionManager.getStatement();
    }
    public ArrayList<User> getQueryResult(Operation operation, User user){
        String query = QueryBuilder.buildQuery(operation, user);
        ResultSet resultSet;
        ArrayList<User> resultList = new ArrayList<>();
        try {
            if (statement.isClosed()) {
                getNewStatement(URL, LOGIN, PASSWORD);
            }
            if(!(operation == Operation.CREATE||operation == Operation.UPDATE||operation == Operation.DELETE)) {
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    User receivedUser = new User(id, name, email, age);
                    resultList.add(receivedUser);
                }
                return resultList;
            }
            else statement.execute(query);

        }catch (SQLException e){
        e.printStackTrace();
        }
        finally{
            dbConnectionManager.closeConnection();
        }
        return resultList;
    }
    public String getErrorMessage(){
        return dbConnectionManager.getErrorMessage();
    }

}

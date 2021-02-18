package com.michael.database.controller;

import com.michael.database.Operation;
import com.michael.database.User;
import com.michael.database.model.Model;

import java.util.List;

public class Controller {
    final Model model;
    final String URL = "jdbc:mysql://localhost:3306/mydbtest";

    public Controller(Model model) {
        this.model = model;
    }

    public boolean login(String login, String password){

        return model.login(URL, login, password);
    }
    public List<User> getResult(Operation operation, User user){
        return model.getQueryResult(operation, user);
    }

    public String getErrorMessage(){
      return model.getErrorMessage();
    }
}

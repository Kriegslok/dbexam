package com.michael.database.command;

import com.michael.database.view.ConsoleHelper;
import com.michael.database.DBModel;

public abstract class EntryCommand implements Command{
    private static final String defaultURL = "jdbc:mysql://localhost:3306/mydbtest";
    private String username;
    private String password;
    public DBModel dbModel(String username, String password) throws Exception{
        ConsoleHelper.writeMessage("Insert DB URL (for default \"jdbc:mysql://localhost:3306/mydbtest\" leave empty)");
        return new DBModel(defaultURL, username, password);
    }
}

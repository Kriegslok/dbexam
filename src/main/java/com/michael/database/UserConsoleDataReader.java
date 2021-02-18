package com.michael.database;

import com.michael.database.view.ConsoleHelper;

import java.io.IOException;

public class UserConsoleDataReader {
    private int id;
    private String username;
    private String email;
    private int age;

    public void readUserId() throws IOException {
        ConsoleHelper.writeMessage("Insert User ID");
        id = ConsoleHelper.readInt();
    }
    public void readUserName() throws IOException {
        ConsoleHelper.writeMessage("Insert User name");
        username = ConsoleHelper.readString();
    }
    public void readUserAge() throws IOException {
        ConsoleHelper.writeMessage("Insert User age");
        age = ConsoleHelper.readInt();
    }

    public void readUserEmail() throws IOException {
        ConsoleHelper.writeMessage("Insert User email");
        email = ConsoleHelper.readString();

    }

    public User getUser() {
        User user = new User(id, username, email, age);
        return user;
    }
}

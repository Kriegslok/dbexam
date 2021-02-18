package com.michael.database.command;

import com.michael.database.User;
import com.michael.database.UserConsoleDataReader;

import java.sql.Statement;
//Класс-модель
public class UpdateCommand extends EntryCommand{
    private String command;
    private String username;
    private String password;
    private User user;
    public UpdateCommand(User user, String username, String password){
        this.user = user;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() throws Exception {
        Statement statement = dbModel(username, password).getStatement();
        if(user == null) {
            UserConsoleDataReader userConsoleDataReader = new UserConsoleDataReader();
            userConsoleDataReader.readUserName();
            userConsoleDataReader.readUserAge();
            userConsoleDataReader.readUserEmail();
            user = userConsoleDataReader.getUser();
        }

        String insertUser = String.format("insert into users (name, age, email) values('%s', %d, '%s');", user.getName(), user.getAge(), user.getEmail());
        statement.execute(insertUser);
    }
}

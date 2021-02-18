package com.michael.database.command;

import com.michael.database.view.ConsoleHelper;
import com.michael.database.User;

import java.sql.ResultSet;
import java.sql.Statement;

//Класс-модель
public class ReadAllCommand extends EntryCommand{
    private User user;
    private int rowLimit;
    private int id;
    private String username;
    private String password;

    public ReadAllCommand(String username, String password){
        this.username = username;
        this.password = password;
    }
    public ReadAllCommand(){
    }
    @Override
    public void execute() throws Exception {
        Statement statement = dbModel(username, password).getStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        ConsoleHelper.writeMessage(resultSet.toString());
        int columns = resultSet.getMetaData().getColumnCount();
        // Перебор строк с данными
        while(resultSet.next()){
            for (int i = 1; i <= columns; i++){
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }

//        String insertUser = String.format("insert into users (name, age, email) values('%s', %d, '%s');", user.getName(), user.getAge(), user.getEmail());
//        statement.execute(insertUser);
    }
//    private static class DBQuery{
//        private static String getQuery() throws IOException {
//            boolean selected = false;
//            int selection = 0;
//            while (!selected){
//            ConsoleHelper.writeMessage("to select user by ID press 1. To select users by name press 2. To select all users press 3");
//            selection = ConsoleHelper.readInt();
//            if((selection > 0) && (selection <=3)) selected = true;
//        }
//    }
}

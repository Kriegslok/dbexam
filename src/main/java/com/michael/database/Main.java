package com.michael.database;

import com.michael.database.controller.Controller;
import com.michael.database.model.Model;
import com.michael.database.view.ConsoleHelper;
import com.michael.database.view.View;

public class Main {

//    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "root";
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);
        view.init();

//    ConsoleHelper.writeMessage("insert '1' to create, '2' to read");
//    int cmd = ConsoleHelper.readInt();
//    if(cmd == 1) CommandController.execute(Operation.CREATE);
//    else if(cmd == 2) CommandController.execute(Operation.READ);
//    else ConsoleHelper.writeMessage("not valid command");
    }
}

package com.michael.database.view;

import com.michael.database.Operation;
import com.michael.database.User;
import com.michael.database.controller.Controller;

import java.io.IOException;
import java.util.List;

public class View {
    private boolean active = true;
    private boolean loginCorrect = false;

    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init(){
        while(!loginCorrect){
            loginCorrect = login();
            if(!loginCorrect) ConsoleHelper.writeMessage(controller.getErrorMessage());
            else {
                ConsoleHelper.writeMessage("logged in");
                ConsoleHelper.writeMessage(controller.getErrorMessage());
                break;
            }
        }
        showResult(Operation.READ_ALL, new User(0, null, null, null));
//        showResult(Operation.READ_BY_ID, new User(1, null, null, null));
//        showResult(Operation.READ_BY_NAME, new User(0, "Ugo", null, null));
//        showResult(Operation.CREATE, new User(0, "UgoS", "ugos@ugos.com", 20));
//        showResult(Operation.CREATE, new User(0, "UgoS", null, null));
//
//        showResult(Operation.READ_ALL, new User(0, null, null, null));
//        showResult(Operation.DELETE, new User(14, null, null, null));
//        showResult(Operation.READ_ALL, new User(0, null, null, null));
        showResult(Operation.UPDATE, new User(16, "UgoGo", "ugugug@ugogo.ugugu", 76));
        showResult(Operation.READ_BY_ID, new User(16, null, null, null));


    }
    private List<User> getResult(Operation operation, User user){
        return controller.getResult(operation, user);
    }

    private void showResult(Operation operation, User user){
        List<User> result = getResult(operation, user);
        for (User receivedUser: result) {
            System.out.println(receivedUser.toString() + "\t");
        }
        ConsoleHelper.writeMessage(controller.getErrorMessage());
    }

    private boolean login(){
        String login;
        String password;
        boolean ok = false;
        try {
            ConsoleHelper.writeMessage("insert login");
            login = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("insert password");
            password = ConsoleHelper.readString();
            ok = controller.login(login, password);
        }catch (IOException e){
            ConsoleHelper.writeMessage(this.getClass().getName() + e.getCause());
        }
        return ok;
    }
}

package com.michael.database;

import com.michael.database.command.*;
import java.util.HashMap;
import java.util.Map;

//Класс-контроллер
public class CommandController {
    private static final Map<Operation, Command> commandMap = new HashMap<>();
    private User user;
    private String password;
    private String username;

    private CommandController() {
    }

    public void execute(Operation operation) throws Exception {
        switch (operation) {
            case CREATE: new CreateCommand(user, username, password).execute();
            break;
            case READ_ALL: new ReadAllCommand(username, password).execute();
            break;
            case UPDATE: new UpdateCommand(user, username, password).execute();
            break;
            //case READONE: new ReadOneCommand(user, username, password);
            //break;
            case DELETE: new DeleteCommand().execute();
            break;
        }
    }
}

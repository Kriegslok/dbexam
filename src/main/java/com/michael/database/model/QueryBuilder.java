package com.michael.database.model;

import com.michael.database.Operation;
import com.michael.database.User;

public class QueryBuilder {
    public static String buildQuery(Operation operation, User user){
        //String query = "";
        switch(operation){
            case READ_ALL: return "select * from users;";
            case CREATE: return String.format("insert into users (name, age, email) values('%s', %d, '%s');", user.getName(), user.getAge(), user.getEmail());
            case READ_BY_ID: return String.format("select * from users where id=%d;", user.getId());
            case READ_BY_NAME: return String.format("select * from users where name = '%s';", user.getName());
            case READ_BY_AGE: return String.format("select * from users where age = %d;", user.getAge());
            case READ_BY_EMAIL: return String.format("select * from users where email = '%s';", user.getEmail());
            case UPDATE: {
                StringBuilder formatBuilder = new StringBuilder();
                formatBuilder.append("update users set ");
                if(user.getName() != null){
                    formatBuilder.append("name = '").append(user.getName()).append("'");
                }
                if(user.getAge() != null){
                    if(formatBuilder.toString().contains("name")){
                        formatBuilder.append(", ");
                    }
                    formatBuilder.append(" age = ").append(user.getAge());
                }
                if(user.getEmail() != null){
                    if(formatBuilder.toString().contains("age")){
                        formatBuilder.append(", ");
                    }
                    formatBuilder.append("email = '").append(user.getEmail()).append("'");
                }
                formatBuilder.append(" where id = ").append(user.getId()).append(";");
                System.out.println(formatBuilder);
                return formatBuilder.toString();
            }
            case DELETE:  return String.format("delete from users where id=%d;", user.getId());
        }
        return "";
    }
}

package main.entities.dao;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import main.entities.User;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super("db/users.json", new TypeToken<ArrayList<User>>(){}.getType());
    }
}

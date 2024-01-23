package lv.fitness_app.services;

import lv.fitness_app.User;
import lv.fitness_app.database.Database;

import java.util.List;

public class GetAllUsersService {

    private Database database;

    public GetAllUsersService(Database database) {
        this.database = database;
    }

    public List<User> execute() {
        return database.getAllUsers();
    }
}

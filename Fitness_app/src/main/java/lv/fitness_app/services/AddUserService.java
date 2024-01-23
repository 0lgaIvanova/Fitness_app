package lv.fitness_app.services;

import lv.fitness_app.User;
import lv.fitness_app.database.Database;

public class AddUserService {

    private Database database;

    public AddUserService(Database database) {
        this.database = database;
    }

    public void execute(String userName, String userPassword) {
        User user = new User(userName, userPassword);
        database.save(user);
    }
}
